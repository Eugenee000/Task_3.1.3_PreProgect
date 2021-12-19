package com.example.task_3_1_1.service;

import com.example.task_3_1_1.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDetails getUserByName(String name);

    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user, Long id);

    void deleteUser(Long id);
}
