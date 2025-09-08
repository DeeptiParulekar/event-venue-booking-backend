package com.example.booking.service;

import com.example.booking.entity.User;

public interface AuthService {

	boolean validateUser(String email, String password);

	User registerUser(User user);

	String generateToken(String email);

	User getUserByEmail(String email);

}
