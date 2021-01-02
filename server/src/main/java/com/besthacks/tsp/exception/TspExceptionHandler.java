package com.besthacks.tsp.exception;

import com.besthacks.tsp.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class TspExceptionHandler {

    @ExceptionHandler(value = TspException.class)
    public ResponseEntity<ErrorResponse> handleTspException(TspException exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(exception.getMessage(), exception.getHttpStatus().value()),
                exception.getHttpStatus()
        );
    }
}
