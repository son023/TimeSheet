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

    IntrospectResponse introspectRespone(IntrospectRequest request) throws JOSEException, ParseException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;

    AuthenticationResponse login(AuthenticationRequest request);

    void logout(LogoutRequest request) throws ParseException, JOSEException;
}
