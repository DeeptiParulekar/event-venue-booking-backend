package com.example.booking.entity;

public class LoginResponse {
	private String message;
	private String token;
	private String email;
	private Long userId;

	public LoginResponse(String message, String token, String email, Long userId) {
		this.message = message;
		this.token = token;
		this.email = email;
		this.userId = userId;
	}

	// Getters and setters
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
}
