package com.besthacks.tsp.mapper;

import com.besthacks.tsp.dto.AccountDto;
import com.besthacks.tsp.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountMapper {

    private PasswordEncoder passwordEncoder;

    public AccountDto toAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .username(account.getUsername())
                .role(account.getAccountRole())
                .points(0)
                .build();
    }

    public Account toAccount(AccountDto accountDto) {
        return Account.builder()
                .email(accountDto.getEmail())
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .city(accountDto.getCity())
                .accountRole(accountDto.getRole())
                .build();
    }
}
