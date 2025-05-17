package com.example.practice_project2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.practice_project2.service.MyAppUserService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private final MyAppUserService appUserService;

    @Bean
    public UserDetailsService userDetailsService() {
        return appUserService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(httpForm -> {
                    httpForm
                        .loginPage("/login")
                        .defaultSuccessUrl("/",true)
                        .failureUrl("/login?error=true")  // show login error
                        .permitAll();
                })

                .oauth2Login(oauth2 -> oauth2
                    .loginPage("/login") // use the same custom login page
                )
                
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/signup", "/login", "/css/**").permitAll();
                    registry.anyRequest().authenticated();
                })
                .build();
    }
}
