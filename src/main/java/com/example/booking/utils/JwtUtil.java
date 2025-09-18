package com.example.booking.utils;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.booking.config.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final JwtConfig jwtConfig;
	
	@Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long JWT_EXPIRATION_MS;

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
		return Jwts.parser().setSigningKey(jwtConfig.getSecret().getBytes()).parseClaimsJws(token).getBody();
	}

//	public String generateToken(String email) {
//		return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
//                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret().getBytes())
//                .compact();
//	}

	public String generateToken(String email, Long userId, String role) {
        return Jwts.builder()
                .setSubject(email)                      // username/email
                .claim("userId", userId)                // user ID claim
                .claim("role", role)                    // role claim
                .setIssuedAt(new Date())                // issued at
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS)) // expiry
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // use secret from properties
                .compact();
    }

	public String extractUsername(String token) {
        return Jwts.parser()
                   .setSigningKey(SECRET_KEY.getBytes())
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	// Validate token
	// Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                              .setSigningKey(SECRET_KEY.getBytes())
                              .parseClaimsJws(token)
                              .getBody()
                              .getExpiration();
        return expiration.before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}
