package com.besthacks.tsp.controller;

import com.besthacks.tsp.dto.AccountDto;
import com.besthacks.tsp.entity.AccountRole;
import com.besthacks.tsp.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    private AccountService accountService;

    @PostMapping
    public AccountDto createAccount(@RequestBody AccountDto request) {
        request.setRole(AccountRole.USER);
        return accountService.createAccount(request);
    }

    @GetMapping("/{id}")
    public AccountDto getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
}
