package com.example.fabriquetesttask.service.implement;

import com.example.fabriquetesttask.dto.UserDto;
import com.example.fabriquetesttask.mapper.UserMapper;
import com.example.fabriquetesttask.model.User;
import com.example.fabriquetesttask.repository.UserRepository;
import com.example.fabriquetesttask.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserDto getUserById(Long id) {
        return userToUserDto(userRepository.getById(id));
    }

    @Override
    public List<UserDto> getAll() {
        return allToDto(userRepository.findAll());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    private UserDto userToUserDto(User user){
        return UserMapper.USER_MAPPER.userToUserDto(user);
    }

    private List<UserDto> allToDto(List<User> users){
        return UserMapper.USER_MAPPER.allToDto(users);
    }
}
