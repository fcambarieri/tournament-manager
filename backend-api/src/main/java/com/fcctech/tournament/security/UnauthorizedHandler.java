package com.fcctech.tournament.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fcctech.tournament.exception.ApiErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException
    ) throws IOException, ServletException {
        ApiErrorResponse apiError = ApiErrorResponse
                .builder()
                .cause(Optional.ofNullable(authException.getCause()).orElse(new Throwable()).getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(authException.getMessage())
                .build();
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, apiError.toString());
    }
}