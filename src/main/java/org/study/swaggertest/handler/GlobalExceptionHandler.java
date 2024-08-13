package org.study.swaggertest.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // TODO : ErrorResponse 객체 만들기

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception caught", e);
        String errMsg = e.getLocalizedMessage();
        return new ResponseEntity<String>("에러 : " + errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticateException(AuthenticationException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception caught", e);
        String errMsg = e.getLocalizedMessage();
        return new ResponseEntity<String>("에러 : " + errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @ExceptionHandler(JwtException.class)

}
