package com.example.booking.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.booking.config.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    // Generate token
//    public String generateToken(String email, Long userId) {
//        return Jwts.builder()
//                .setSubject(email)
//                .claim("userId", userId)  // add userId
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
//                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret().getBytes())
//                .compact();
//    }
    
    public String generateToken(String email, Long userId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)   // <<< add this line
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret().getBytes())
                .compact();
    }


    
    public Long extractUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }


    // Extract email (subject) from token
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Helper method to parse claims
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
