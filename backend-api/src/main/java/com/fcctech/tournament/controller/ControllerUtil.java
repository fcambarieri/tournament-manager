package com.fcctech.tournament.controller;

import com.fcctech.tournament.exception.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerUtil {

    public static ResponseEntity<ApiErrorResponse> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiErrorResponse.builder()
                        .message(message)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build()
                );

    }

    public static ResponseEntity accessDenied(String message) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiErrorResponse.builder()
                        .message(message)
                        .statusCode(HttpStatus.FORBIDDEN.value())
                        .build()
                );
    }
}
