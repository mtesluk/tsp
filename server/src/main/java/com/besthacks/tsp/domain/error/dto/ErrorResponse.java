package com.besthacks.tsp.domain.error.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String message;
    private Integer httpStatus;
}
