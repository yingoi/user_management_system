package com.vnn.usermanagement.service;

import com.vnn.usermanagement.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public User register(User user);
}
