package com.besthacks.tsp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private Long accountId;
    private String token;
    private Integer expiresIn;
}
