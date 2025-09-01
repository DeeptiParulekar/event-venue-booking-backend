package com.example.booking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .antMatchers("/api/auth/**").permitAll()   // allow auth APIs
                .antMatchers("/api/public/**").permitAll() // allow public APIs
                .antMatchers("/api/dashboard/**").permitAll() // allow dashboard APIs
                .antMatchers("/api/venues/**").permitAll()    // <<< add this line for venues APIs
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {}) // basic auth if needed
            .formLogin(form -> form.disable()); // disable form login

        return http.build();
    }
}
