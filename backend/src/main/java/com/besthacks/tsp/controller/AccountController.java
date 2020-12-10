package com.besthacks.tsp.controller;

import com.besthacks.tsp.domain.account.dto.AccountCreateRequest;
import com.besthacks.tsp.domain.account.dto.AccountResponse;
import com.besthacks.tsp.handler.AccountHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    private AccountHandler accountHandler;

    @PostMapping
    public AccountResponse createAccount(@RequestBody AccountCreateRequest request) {
        return accountHandler.createAccount(request);
    }

    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable Long id) {
        return accountHandler.getAccountById(id);
    }
}
