package com.besthacks.tsp.config.auth;

import com.besthacks.tsp.service.TspUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static java.util.Collections.singletonList;


@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private TspUserDetailsService tspUserDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(tspUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/login", "/api/v1/accounts").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(STATELESS)
                .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.cors().configurationSource(corsConfigurationSource());
    }

    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        List<String> allowOrigins = Arrays.asList("https://tsp-app.netlify.app");
        configuration.setAllowedOrigins(allowOrigins);
        configuration.setAllowedMethods(singletonList("*"));
        configuration.setAllowedHeaders(singletonList("*"));
        //in case authentication is enabled this flag MUST be set, otherwise CORS requests will fail
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
