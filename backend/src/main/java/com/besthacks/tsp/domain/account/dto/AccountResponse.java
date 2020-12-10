package com.besthacks.tsp.domain.account.dto;

import com.besthacks.tsp.domain.account.entity.AccountRole;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountResponse {
    private Long id;
    private String email;
    private String username;
    private AccountRole role;
    private Integer points;
}
