package com.besthacks.tsp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TspException extends RuntimeException {

    private HttpStatus httpStatus;

    public TspException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
