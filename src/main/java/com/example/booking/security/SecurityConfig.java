package com.example.booking.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
//            .authorizeRequests(requests -> requests
//                .antMatchers("/api/auth/**", "/api/public/**").permitAll()
//                .antMatchers("/api/dashboard/**").hasAuthority("ADMIN")
//                .antMatchers("/api/venues/**").hasAuthority("USER")
//                .anyRequest().authenticated()
//            )
            
            .authorizeRequests(requests -> requests
            	    .antMatchers("/api/auth/**", "/api/public/**").permitAll()
            	    .antMatchers("/api/venues/**").permitAll() // 👈 venues are public
            	    .antMatchers("/api/booking/**").hasAuthority("USER") // 👈 only logged-in users can book
            	    .antMatchers("/api/dashboard/**").hasAuthority("ADMIN")
            	    .anyRequest().authenticated()
            	)

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .httpBasic(withDefaults())
            .formLogin(login -> login.disable());

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   
}
