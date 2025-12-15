package com.hugecorp.teamwalk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Object> handleUnsupportedOperationException(UnsupportedOperationException ex) {
        // Return a 422 Unprocessable Entity status with the exception message
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_CONTENT);
    }
}