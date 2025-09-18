package com.example.booking.service;

import java.util.List;

import com.example.booking.dto.UserDTO;
import com.example.booking.entity.User;

public interface UserService {

	UserDTO createUser(UserDTO userDTO);

	UserDTO updateUser(Long userId, UserDTO userDTO);

	List<UserDTO> getAllUsers();

	UserDTO getUserById(Long userId);

	void deleteUser(Long userId);

	User getUserByEmail(String email);

}
