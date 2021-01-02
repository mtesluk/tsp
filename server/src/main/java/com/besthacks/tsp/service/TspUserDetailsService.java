package com.besthacks.tsp.service;

import com.besthacks.tsp.entity.Account;
import com.besthacks.tsp.exception.TspException;
import com.besthacks.tsp.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@AllArgsConstructor
public class TspUserDetailsService implements UserDetailsService {
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findAccountByUsername(username)
                .map(this::toUser)
                .orElseThrow(() -> new TspException("User with username " + username + " not found", UNAUTHORIZED));
    }

    private User toUser(Account account) {
        return new User(account.getUsername(), account.getPassword(), new HashSet<>());
    }
}
