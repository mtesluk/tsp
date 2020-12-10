package com.besthacks.tsp.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequest {
    private String email;
    private String username;
    private String city;
    private String password;
}
