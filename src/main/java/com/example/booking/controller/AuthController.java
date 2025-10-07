package com.example.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.dto.AuthRequest;
import com.example.booking.dto.AuthResponse;
import com.example.booking.entity.User;
import com.example.booking.service.AuthService;
import com.example.booking.service.UserService;
import com.example.booking.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
	        );

	        User user = authService.getUserByEmail(request.getEmail());

//	        String token = jwtUtil.generateToken(user.getEmail(), user.getUserId());
	        String token = jwtUtil.generateToken(user.getEmail(), user.getUserId(), user.getRole());

	        return ResponseEntity.ok(new AuthResponse(
	                "Login Successful",
	                token,
	                user.getEmail(),
	                user.getUserId(),
	                user.getRole()
	        ));

	    } catch (BadCredentialsException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(new AuthResponse("Invalid Credentials", null, null, null, null));
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
