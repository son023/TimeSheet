package org.example.testspringcsdl.service;

import java.text.ParseException;

import org.example.testspringcsdl.dto.request.AuthenticationRequest;
import org.example.testspringcsdl.dto.request.IntrospectRequest;
import org.example.testspringcsdl.dto.request.LogoutRequest;
import org.example.testspringcsdl.dto.request.RefreshRequest;
import org.example.testspringcsdl.dto.respone.AuthenticationResponse;
import org.example.testspringcsdl.dto.respone.IntrospectResponse;

import com.nimbusds.jose.JOSEException;

public interface IAuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request) throws JOSEException;

    IntrospectResponse introspectRespone(IntrospectRequest request) throws JOSEException, ParseException;
    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
}
