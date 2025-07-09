package com.cybersoft.capstone.config;

import com.cybersoft.capstone.filter.CustomAuthenticationFilter;
import com.cybersoft.capstone.service.implement.AdminDetailsServiceImpl;
import com.cybersoft.capstone.service.implement.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsServiceImpl userDetailsServiceImpl;
    private AdminDetailsServiceImpl adminDetailsServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationFilter customAuthenticationFilter) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/api/auth/login").permitAll();
                    request.requestMatchers("/api/checkout").hasAuthority("ROLE_USER");
                    request.requestMatchers("/api/checkout/fullfill").hasAuthority("ROLE_USER");

                    request.requestMatchers("/admin/register").permitAll();
                    request.requestMatchers("/admin/login").permitAll();
                    request.requestMatchers("/admin/game/no-auth").permitAll();
                    request.requestMatchers("/admin/**").hasAnyAuthority("ROLE_SUPER_ADMIN");
                    request.anyRequest().permitAll();
                })
                .cors(Customizer.withDefaults())
                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
