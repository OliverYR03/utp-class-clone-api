package com.zenkai.pe.UniversidadPOO.config;

import com.zenkai.pe.UniversidadPOO.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/student/me/**").hasRole("ESTUDIANTE")
                .requestMatchers("/profesor/me/**").hasRole("PROFESOR")
                .requestMatchers("/api/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .userDetailsService(userDetailsService)
            .formLogin(Customizer.withDefaults())
            .build();
    }
}