package com.besthacks.tsp.domain.account.mapper;

import com.besthacks.tsp.domain.account.dto.AccountCreateRequest;
import com.besthacks.tsp.domain.account.dto.AccountResponse;
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

    public AccountResponse mapToAccountResponse(Account account, List<Reward> rewards) {
        return AccountResponse.builder()
                .id(account.getId())
                .email(account.getEmail())
                .username(account.getUsername())
                .role(account.getAccountRole())
                .points(rewards.stream().map(Reward::getAmount).reduce(0, Integer::sum))
                .build();
    }

    public Account mapToAccount(AccountCreateRequest request) {
        return Account.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .city(request.getCity())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountRole(AccountRole.USER)
                .build();
    }
}
