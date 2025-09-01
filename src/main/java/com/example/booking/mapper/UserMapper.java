package com.example.booking.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.booking.dto.UserDTO;
import com.example.booking.entity.User;

@Component
public class UserMapper {

    // DTO -> Entity
    public User toUser(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUserId(dto.getUserId());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    // Entity -> DTO
    public UserDTO toUserDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }

    // List<Entity> -> List<DTO>
    public List<UserDTO> toUserDTOList(List<User> users) {
        List<UserDTO> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(toUserDTO(user));
        }
        return dtos;
    }

    // List<DTO> -> List<Entity>
    public List<User> toUserList(List<UserDTO> dtos) {
        List<User> entities = new ArrayList<>();
        for (UserDTO dto : dtos) {
            entities.add(toUser(dto));
        }
        return entities;
    }
}
