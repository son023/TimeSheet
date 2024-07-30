package org.example.testspringcsdl.filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.testspringcsdl.components.JwtTokenUtils;
import org.example.testspringcsdl.configuration.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
    @Component
    @RequiredArgsConstructor
    @Slf4j

    public class JwtTokenFilter extends OncePerRequestFilter{
        private final CustomUserDetailsService customUserDetailsService;
        private final JwtTokenUtils jwtTokenUtils;
        @Override
        protected void doFilterInternal(@NonNull  HttpServletRequest request,
                                        @NonNull HttpServletResponse response,
                                        @NonNull FilterChain filterChain)
                throws ServletException, IOException {
            try {
                if(jwtTokenUtils.isBypassToken(request)) {
                    filterChain.doFilter(request, response); //enable bypass
                    return;
                }
                final String authHeader = request.getHeader("Authorization");
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    return;
                }
                final String token = authHeader.substring(7);
                SignedJWT signedJWT = SignedJWT.parse(token);
                final String userName =signedJWT.getJWTClaimsSet().getSubject();

                    if (jwtTokenUtils.isLogout(signedJWT)) {
                        logger.warn("Token has been logged out: " + token);
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                        return;
                    }


                    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
                        logger.error(userDetails.getUsername());
                        if (jwtTokenUtils.validateToken(token,  userDetails)) {
                            logger.error(userDetails.getUsername());
                            UsernamePasswordAuthenticationToken authenticationToken =
                                    new UsernamePasswordAuthenticationToken(
                                            userDetails,
                                            null,
                                            userDetails.getAuthorities()
                                    );
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }
                    }
                log.info("Current Authentication: " + SecurityContextHolder.getContext().getAuthentication());
                if (SecurityContextHolder.getContext().getAuthentication() != null) {
                    log.info("Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                }

                filterChain.doFilter(request, response);
            }catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                logger.error(e);
            }

        }
}
