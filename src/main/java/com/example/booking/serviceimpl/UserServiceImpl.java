package com.example.booking.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.booking.dto.UserDTO;
import com.example.booking.entity.User;
import com.example.booking.mapper.UserMapper;
import com.example.booking.repository.UserRepository;
import com.example.booking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        Optional<User> existingUserOpt = userRepository.findById(userId);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setPassword(userDTO.getPassword());
            existingUser.setRole(userDTO.getRole());

            User updatedUser = userRepository.save(existingUser);
            return userMapper.toUserDTO(updatedUser);
        }
        return null; // or throw custom exception
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTOList(users);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        return userOpt.map(userMapper::toUserDTO).orElse(null);
    }

    @Override
    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }

    @Override
	@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN', 'CUSTOMER')")
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}
}
