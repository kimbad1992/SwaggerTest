package org.study.swaggertest.handler;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.study.swaggertest.model.ErrorResponse;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception caught", e);
        return new ResponseEntity<>(createErrorResponse(e, request, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticateException(AuthenticationException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception caught", e);
        return new ResponseEntity<>(createErrorResponse(e, request, HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception caught", e);
        return new ResponseEntity<>(createErrorResponse(e, request, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeException(RuntimeException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception caught", e);
        return new ResponseEntity<>(createErrorResponse(e, request, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }


    private ErrorResponse createErrorResponse(Exception e, HttpServletRequest request, HttpStatus status) {
        return new ErrorResponse(
                e.getClass().getSimpleName(),
                e.getMessage(),
                request.getRequestURI(),
                status.value(),
                LocalDateTime.now()
        );
    }
}
