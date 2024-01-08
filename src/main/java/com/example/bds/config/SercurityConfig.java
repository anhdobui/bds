package com.example.bds.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SercurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         http.csrf().disable()
                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                 .authorizeHttpRequests(request -> {
                     request
                             .requestMatchers("/api/auth/**").permitAll()
                             .requestMatchers("/api/building/**","/api/building").permitAll()
                             .requestMatchers("/api/district","/api/user/all-staff").permitAll()
                             .anyRequest().authenticated();
                 })
                 .authenticationProvider(authenticationProvider)
                 .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         return http.build();
    }

}
