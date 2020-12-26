package com.besthacks.tsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@SpringBootApplication
public class TspApplication {

    public static void main(String[] args) {
        System.out.println("HASLO " + new BCryptPasswordEncoder(10, new SecureRandom()).encode("pass"));
        SpringApplication.run(TspApplication.class, args);
    }

}
