package com.besthacks.tsp.service;

import com.besthacks.tsp.dto.AccountDto;
import com.besthacks.tsp.mapper.AccountMapper;
import com.besthacks.tsp.exception.TspException;
import com.besthacks.tsp.repository.AccountRepository;
import com.besthacks.tsp.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private AccountMapper accountMapper;

    public AccountDto loadAccountByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findAccountByUsername(username)
                .map(accountMapper::toAccountDto)
                .orElseThrow(() -> new TspException("User with username " + username + " not found", UNAUTHORIZED));
    }



    public AccountDto createAccount(AccountDto request) {
        return accountMapper.toAccountDto(accountRepository.save(accountMapper.toAccount(request)));
    }

    public AccountDto getAccountById(Long id) {
        return accountMapper.toAccountDto(accountRepository.getOne(id));
    }

    public AccountDto getAccountByUsername(String username) {
        return accountMapper.toAccountDto(accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new TspException("User with username " + username + " not found", UNAUTHORIZED)));
    }
}
