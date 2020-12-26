package com.besthacks.tsp.controller;

import com.besthacks.tsp.domain.login.dto.LoginRequest;
import com.besthacks.tsp.domain.login.dto.LoginResponse;
import com.besthacks.tsp.handler.LoginHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/login")
public class LoginController {

    private LoginHandler loginHandler;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginHandler.login(request);
    }

}
