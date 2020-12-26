package com.besthacks.tsp.handler;

import com.besthacks.tsp.domain.account.dto.AccountDto;
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

    public AccountDto createAccount(AccountDto request) {
        return accountMapper.toAccountDto(accountRepository.save(accountMapper.toAccount(request)), new ArrayList<>());
    }

    public AccountDto getAccountById(Long id) {
        return accountMapper.toAccountDto(accountRepository.getOne(id), rewardRepository.findByAccountId(id));
    }

    public AccountDto getAccountByUsername(String username) {
        return accountMapper.toAccountDto(accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new TspException("User with username " + username + " not found", UNAUTHORIZED)), new ArrayList<>());
    }

}
