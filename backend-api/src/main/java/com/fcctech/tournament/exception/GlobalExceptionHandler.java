package com.fcctech.tournament.exception;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.fcctech.tournament.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<?> validationException(BusinessException ex) {
        log.error("BusinessException exception : " +
                ex.getLocalizedMessage()
        );
       return ResponseEntity
               .status(ex.getStatus())
               .body(ExceptionUtil.error(ex));
    }

    @ExceptionHandler({ Throwable.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        log.error("Fuck exception : " +
                ex.getLocalizedMessage()
        , ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionUtil.error(ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(StringUtil.camelToSnake(fieldName), errorMessage);
        });

        String cause = errors.entrySet().stream().map(stringStringEntry -> stringStringEntry.getKey() + ": " + stringStringEntry.getValue() + ", ").collect(Collectors.joining());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiErrorResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("invalid fields request")
                        .cause(cause)
                        .build());

    }


    @ExceptionHandler(ValueInstantiationException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(ValueInstantiationException ex) {
        String cause = ex.getLocalizedMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiErrorResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("invalid fields request")
                        .cause(cause)
                        .build());

    }


}


