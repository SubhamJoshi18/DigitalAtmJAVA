package com.subham.ATM.config;


import com.subham.ATM.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {


    @Autowired
    JwtAuthFilter jwtAuthFilter;


    @Autowired
    AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityilterChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer :: disable).authorizeHttpRequests(req -> req.requestMatchers("/account/auth/*").permitAll().anyRequest().authenticated()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
