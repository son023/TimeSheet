package org.example.testspringcsdl.controller;

import java.text.ParseException;

import org.example.testspringcsdl.dto.ApiResponse;
import org.example.testspringcsdl.dto.request.AuthenticationRequest;
import org.example.testspringcsdl.dto.request.IntrospectRequest;
import org.example.testspringcsdl.dto.request.LogoutRequest;
import org.example.testspringcsdl.dto.request.RefreshRequest;
import org.example.testspringcsdl.dto.respone.AuthenticationResponse;
import org.example.testspringcsdl.dto.respone.IntrospectResponse;
import org.example.testspringcsdl.service.impl.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse result = null;
        try {
            result = authenticationService.authenticate(request);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        return ApiResponse.<AuthenticationResponse>builder()
                .code(999)
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        log.error("loi o day");
        IntrospectResponse result = authenticationService.introspectRespone(request);

        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }
}
