package com.example.fabriquetesttask.service.interfaces;

import com.example.fabriquetesttask.dto.UserDto;
import com.example.fabriquetesttask.model.User;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    List<UserDto> getAll();
    User findByUsername(String username);
    void save(User user);
}
