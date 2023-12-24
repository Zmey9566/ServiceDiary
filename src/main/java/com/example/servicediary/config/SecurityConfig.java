package com.example.servicediary.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/rest/mentor/**", "/mentor/**", "/")
                                    .hasAnyRole("MENTOR", "ADMIN")
                                .requestMatchers( "/student/**")
                                    .hasAnyRole("STUDENT", "MENTOR", "ADMIN")
                                .anyRequest().authenticated()
                )
                .logout(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults());
                .formLogin(login -> login.successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        final var roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

                        if (roles.contains("ROLE_ADMIN")) {
                            response.sendRedirect("");
                        } else if (roles.contains("ROLE_MENTOR")) {
                            response.sendRedirect("");
                        } else if (roles.contains("ROLE_STUDENT")) {
                            response.sendRedirect("/student");
                        }
                    }}));
        return http.build();
        }
    }
