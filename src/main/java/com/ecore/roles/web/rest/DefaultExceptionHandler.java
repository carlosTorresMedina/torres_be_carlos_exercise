package com.ecore.roles.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.ecore.roles.exception.ErrorResponse;
import com.ecore.roles.exception.InvalidLogicException;
import com.ecore.roles.exception.ResourceExistsException;
import com.ecore.roles.exception.ResourceNotFoundException;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(ResourceNotFoundException exception) {
        return createResponse(404, exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(ResourceExistsException exception) {
        return createResponse(400, exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(InvalidLogicException exception) {
        return createResponse(400, exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(IllegalStateException exception) {
        return createResponse(500, exception.getMessage());
    }

    private ResponseEntity<ErrorResponse> createResponse(int status, String exception) {
        return ResponseEntity
                .status(status)
                .body(ErrorResponse.builder()
                        .status(status)
                        .error(exception).build());
    }
}
