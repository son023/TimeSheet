package org.example.testspringcsdl.components;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.util.Pair;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.testspringcsdl.entity.User;
import org.example.testspringcsdl.exception.AppException;
import org.example.testspringcsdl.exception.ErrorCode;
import org.example.testspringcsdl.repository.InvalidatedTokenRepository;
import org.example.testspringcsdl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtTokenUtils {
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;
    @NonFinal
    @Value("${jwt.api-prefix}")
    String apiPrefix;

    InvalidatedTokenRepository invalidatedTokenRepository;

    public String generateToken(Authentication authentication, boolean isRefresh) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(authentication.getName())
                .issuer("123456")
                .issueTime(new Date())
                .expirationTime(
                        new Date(Instant.now().plus(isRefresh==true ? 3000: 1000, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Không thể tạo token", e);
            throw new RuntimeException(e);
        }
    }

    public boolean validateToken(String token,  UserDetails user) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier((SIGNER_KEY.getBytes()));
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean verified = signedJWT.verify(verifier);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        String userName=signedJWT.getJWTClaimsSet().getSubject();

        return verified && expiryTime.after(new Date()) && user.getUsername().equals(userName) ;
    }
    public boolean isBypassToken(HttpServletRequest request) {
        List<Pair<String, String>> bypassTokens = Arrays.asList(
                Pair.of(String.format("%s/auth/logout", apiPrefix), "POST"),
                Pair.of(String.format("%s/auth/introspect", apiPrefix), "POST"),
                Pair.of(String.format("%s/auth/login", apiPrefix), "POST"),
                Pair.of(String.format("%s/auth/refresh", apiPrefix), "POST")
        );

        String requestPath = request.getServletPath();
        String requestMethod = request.getMethod();

        for (Pair<String, String> bypassToken : bypassTokens) {
            if (requestPath.contains(bypassToken.getLeft())
                    && requestMethod.equals(bypassToken.getRight())) {
                return true;
            }
        }

        return false;
    }

    public boolean isLogout(SignedJWT signedJWT) throws ParseException {
        return invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())? true:false;
    }




}