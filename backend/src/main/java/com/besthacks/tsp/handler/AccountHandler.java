package com.besthacks.tsp.handler;

import com.besthacks.tsp.domain.account.dto.AccountCreateRequest;
import com.besthacks.tsp.domain.account.dto.AccountResponse;
import com.besthacks.tsp.domain.account.mapper.AccountMapper;
import com.besthacks.tsp.exception.TspException;
import com.besthacks.tsp.repository.AccountRepository;
import com.besthacks.tsp.repository.RewardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@AllArgsConstructor
public class AccountHandler {

    private AccountRepository accountRepository;
    private RewardRepository rewardRepository;
    private AccountMapper accountMapper;

    public AccountResponse createAccount(AccountCreateRequest request) {
        return accountMapper.mapToAccountResponse(
                accountRepository.save(accountMapper.mapToAccount(request)), new ArrayList<>());
    }

    public AccountResponse getAccountById(Long id) {
        return accountMapper.mapToAccountResponse(accountRepository.getOne(id), rewardRepository.findByAccountId(id));
    }

    public AccountResponse getAccountByUsername(String username) {
        return accountMapper.mapToAccountResponse(accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new TspException("User with username " + username + " not found", UNAUTHORIZED)), new ArrayList<>());
    }

}
