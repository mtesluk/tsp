package com.besthacks.tsp.controller;

import com.besthacks.tsp.domain.account.dto.AccountDto;
import com.besthacks.tsp.domain.account.entity.AccountRole;
import com.besthacks.tsp.handler.AccountHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    private AccountHandler accountHandler;

    @PostMapping
    public AccountDto createAccount(@RequestBody AccountDto request) {
        request.setRole(AccountRole.USER);
        return accountHandler.createAccount(request);
    }

    @GetMapping("/{id}")
    public AccountDto getAccountById(@PathVariable Long id) {
        return accountHandler.getAccountById(id);
    }
}
