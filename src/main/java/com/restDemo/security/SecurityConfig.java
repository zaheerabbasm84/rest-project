package com.restDemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()          // ← no auth restrictions on anything
                )
                .csrf(csrf -> csrf.disable())          // ← no CSRF blocks on any endpoint
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()) // ← allows H2 iframe to load
                );

        return http.build();
    }
}