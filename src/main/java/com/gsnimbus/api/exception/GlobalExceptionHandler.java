package com.gsnimbus.api.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", LocalDateTime.now());
        resposta.put("status", HttpStatus.BAD_REQUEST.value());

        Map<String, String> errors = violations.stream().collect(Collectors.toMap(
                v -> v.getPropertyPath().toString(),
                ConstraintViolation::getMessage
        ));
        resposta.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<Object> handleTokenError(TokenValidationException e) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("error", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(resposta);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<Object> handleUserNotAuthorized(UserNotAuthorizedException e){
        Map<String, String> resposta = new HashMap<>();
        resposta.put("error", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(resposta);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ApiFetchErrorException.class)
    public ResponseEntity<String> handleApiFetchError(ApiFetchErrorException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
    }

    @ExceptionHandler(ConversionErrorException.class)
    public ResponseEntity<String> handleConversionError(ConversionErrorException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
