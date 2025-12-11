package com.example.userservice.service;

import com.example.userservice.Repository.UserRepository;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = UserMapper.toModel(userRequestDto);
        User newUser = userRepository.save(user);
        return UserMapper.toDTO(newUser);
    }
}
