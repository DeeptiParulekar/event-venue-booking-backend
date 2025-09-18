package com.example.booking.dto;

public class AuthResponse {

	private String message;
	private String token;
	private String email;
	private Long userId;
	private String role;

	public AuthResponse() {
	}

	public AuthResponse(String message, String token, String email, Long userId, String role) {
		this.message = message;
		this.token = token;
		this.email = email;
		this.userId = userId;
		this.role = role;
	}

	// Getters and Setters
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
