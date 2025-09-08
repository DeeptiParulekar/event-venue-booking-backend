package com.example.booking.serviceimpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.booking.entity.User;
import com.example.booking.repository.UserRepository;
import com.example.booking.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private String secretKey = "mySuperSecretKey12345"; // Use application.properties instead

	@Override
	public boolean validateUser(String email, String rawPassword) {
		Optional<User> userOpt = userRepository.findByEmail(email);
		return userOpt.isPresent() && passwordEncoder.matches(rawPassword, userOpt.get().getPassword());
	}

	@Override
	public User registerUser(User user) {
		// Encrypt password before saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public String generateToken(String email) {
		long expirationTime = 1000 * 60 * 60; // 1 hour

		return Jwts.builder().setSubject(email).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}

}
