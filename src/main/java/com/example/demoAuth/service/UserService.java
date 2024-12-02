package com.example.demoAuth.service;

import com.example.demoAuth.dto.UserDto;
import com.example.demoAuth.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<User> findAllUsers();
}
