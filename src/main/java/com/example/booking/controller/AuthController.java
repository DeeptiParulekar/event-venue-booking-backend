package com.example.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			User dbUser = authService.getUserByEmail(user.getEmail());
			String token = jwtUtil.generateToken(dbUser.getEmail(), dbUser.getUserId());

			LoginResponse response = new LoginResponse("Login Success", token, dbUser.getEmail(), dbUser.getUserId());

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(401).body(new LoginResponse("Invalid Credentials", null, null, null));
		}
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		User savedUser = authService.registerUser(user);
		return ResponseEntity.ok(savedUser);
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestParam String email) {
		String response = authService.forgotPassword(email);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {

		String response = authService.resetPassword(token, newPassword);
		return ResponseEntity.ok(response);
	}
}
