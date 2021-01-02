package com.besthacks.tsp.service;

import com.besthacks.tsp.dto.LoginRequest;
import com.besthacks.tsp.dto.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private TspUserDetailsService tspUserDetailsService;
    private JwtTokenService jwtTokenService;
    private AccountService accountService;

    public LoginResponse login(LoginRequest request) {
        return new LoginResponse(
                accountService.getAccountByUsername(request.getUsername()).getId(),
                jwtTokenService.generateToken(tspUserDetailsService.loadUserByUsername(request.getUsername())),
                jwtTokenService.getValidityMs()
        );
    }
}
