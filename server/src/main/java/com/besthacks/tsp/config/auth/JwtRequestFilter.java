package com.besthacks.tsp.config.auth;

import com.besthacks.tsp.exception.TspException;
import com.besthacks.tsp.service.JwtTokenService;
import com.besthacks.tsp.service.TspUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtTokenService jwtTokenService;
    private TspUserDetailsService tspUserDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest httpServletRequest) {
        return Stream.of("/api/v1/login", "/api/v1/accounts").anyMatch(path -> path.equals(httpServletRequest.getRequestURI()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(httpServletRequest);
        if (jwtTokenService.isTokenValid(token)) {
            String username = jwtTokenService.getUsernameFromToken(token);
            UserDetails userDetails = tspUserDetailsService.loadUserByUsername(username);
            SecurityContextHolder.getContext().setAuthentication(createUsernamePasswordAuthenticationToken(httpServletRequest, userDetails));
        } else {
            throw new TspException("Authorization token in invalid", UNAUTHORIZED);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String extractToken(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader(AUTHORIZATION))
                .filter(h -> h.startsWith("Bearer "))
                .map(h -> h.substring(7))
                .orElseThrow(() -> new TspException("Unable to extract token from request headers", UNAUTHORIZED));
    }

    private UsernamePasswordAuthenticationToken createUsernamePasswordAuthenticationToken(HttpServletRequest httpServletRequest, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        return token;
    }

}
