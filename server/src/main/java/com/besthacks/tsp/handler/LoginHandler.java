package com.besthacks.tsp.handler;

import com.besthacks.tsp.domain.login.dto.LoginRequest;
import com.besthacks.tsp.domain.login.dto.LoginResponse;
import com.besthacks.tsp.service.JwtTokenService;
import com.besthacks.tsp.service.TspUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginHandler {

    private TspUserDetailsService tspUserDetailsService;
    private JwtTokenService jwtTokenService;
    private AccountHandler accountHandler;

    public LoginResponse login(LoginRequest request) {
        return new LoginResponse(
                accountHandler.getAccountByUsername(request.getUsername()).getId(),
                jwtTokenService.generateToken(tspUserDetailsService.loadUserByUsername(request.getUsername())),
                jwtTokenService.getValidityMs()
        );
    }
}
