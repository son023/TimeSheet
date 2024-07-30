package org.example.testspringcsdl.service.impl;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.example.testspringcsdl.components.JwtTokenUtils;
import org.example.testspringcsdl.configuration.CustomUserDetailsService;
import org.example.testspringcsdl.dto.request.AuthenticationRequest;
import org.example.testspringcsdl.dto.request.IntrospectRequest;
import org.example.testspringcsdl.dto.request.LogoutRequest;
import org.example.testspringcsdl.dto.request.RefreshRequest;
import org.example.testspringcsdl.dto.respone.AuthenticationResponse;
import org.example.testspringcsdl.dto.respone.IntrospectResponse;
import org.example.testspringcsdl.entity.InvalidatedToken;
import org.example.testspringcsdl.exception.AppException;
import org.example.testspringcsdl.exception.ErrorCode;
import org.example.testspringcsdl.repository.InvalidatedTokenRepository;
import org.example.testspringcsdl.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {
    CustomUserDetailsService customUserDetailsService;
    InvalidatedTokenRepository invalidatedTokenRepository;
    JwtTokenUtils jwtTokenUtils;
    AuthenticationManager authenticationManager;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    @Override
    public IntrospectResponse introspectRespone(IntrospectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }
        return IntrospectResponse.builder().valid(isValid).build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassWord()));

        String token = jwtTokenUtils.generateToken(authentication, false);
        String refreshToken = jwtTokenUtils.generateToken(authentication, true);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .authenticated(true)
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getRefreshToken(), true);
        var userName = signedJWT.getJWTClaimsSet().getSubject();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null);

        String token = jwtTokenUtils.generateToken(authentication, false);
        String refreshToken = jwtTokenUtils.generateToken(authentication, true);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .authenticated(true)
                .build();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), false);
            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();
            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();
            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException e) {
            log.info("Token already expired");
        }
    }

    public SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier((SIGNER_KEY.getBytes()));
        SignedJWT signedJWT = SignedJWT.parse(token);

        boolean verified = signedJWT.verify(verifier);
        Date expiryTime = isRefresh
                ? new Date(signedJWT
                        .getJWTClaimsSet()
                        .getIssueTime()
                        .toInstant()
                        .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (isLogout(signedJWT)) throw new AppException(ErrorCode.UNAUTHENTICATED);
        return signedJWT;
    }

    public boolean isLogout(SignedJWT signedJWT) throws ParseException {
        return invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()) ? true : false;
    }
}
