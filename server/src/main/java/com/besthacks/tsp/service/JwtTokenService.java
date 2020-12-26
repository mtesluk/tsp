package com.besthacks.tsp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.lang.System.currentTimeMillis;

@Component
public class JwtTokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.validity.ms}")
    private Integer validityMs;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(currentTimeMillis()))
                .setExpiration(new Date(currentTimeMillis() + validityMs))
                .signWith(HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Integer getValidityMs() {
        return validityMs;
    }

    public boolean isTokenValid(String token) {
        return !getClaimsFromToken(token).getExpiration().before(new Date(currentTimeMillis()));
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
