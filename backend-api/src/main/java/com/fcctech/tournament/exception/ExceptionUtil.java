package com.fcctech.tournament.exception;

import org.springframework.http.HttpStatus;

import java.util.Objects;
public class ExceptionUtil {

    private static ApiErrorResponse businessError(BusinessException ex) {
        return ApiErrorResponse
                .builder()
                .cause(cause(ex))
                .statusCode(ex.getStatus().value())
                .message(ex.getMessage())
                .build();
    }

    public static ApiErrorResponse error(Throwable ex) {
        if (ex instanceof BusinessException) {
            return businessError((BusinessException) ex);
        }

        return ApiErrorResponse
                .builder()
                .cause(cause(ex))
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getLocalizedMessage())
                .build();
    }

    public static String cause(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause.getMessage();
    }
}
