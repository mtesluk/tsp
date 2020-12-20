package com.besthacks.tsp.domain.account.mapper;

import com.besthacks.tsp.domain.account.dto.AccountDto;
import com.besthacks.tsp.domain.account.entity.Account;
import com.besthacks.tsp.domain.account.entity.AccountRole;
import com.besthacks.tsp.domain.reward.entity.Reward;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AccountMapper {

    private PasswordEncoder passwordEncoder;

    public AccountDto toAccountDto(Account account, List<Reward> rewards) {
        return AccountDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .username(account.getUsername())
                .role(account.getAccountRole())
                .points(rewards.stream().map(Reward::getAmount).reduce(0, Integer::sum))
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
