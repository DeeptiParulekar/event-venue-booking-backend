package com.example.booking.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.entity.LoginResponse;
import com.example.booking.entity.User;
import com.example.booking.service.AuthService;
import com.example.booking.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody User user) {
	    boolean valid = authService.validateUser(user.getEmail(), user.getPassword());

	    if (valid) {
	        User dbUser = authService.getUserByEmail(user.getEmail()); // fetch full user
	        String token = jwtUtil.generateToken(dbUser.getEmail(), dbUser.getUserId());

	        LoginResponse response = new LoginResponse(
	            "Login Success",
	            token,
	            dbUser.getEmail(),
	            dbUser.getUserId()
	        );

	        return ResponseEntity.ok(response);
	    } else {
	        LoginResponse response = new LoginResponse(
	            "Invalid Credentials",
	            null,
	            null,
	            null
	        );
	        return ResponseEntity.status(401).body(response);
	    }
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		User savedUser = authService.registerUser(user);
		return ResponseEntity.ok(savedUser);
	}
}
