package com.plsnogod.jmboot.service;

import com.plsnogod.jmboot.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> showAllUsers();
    User updateUser(User user);
    void deleteUser(long id);
    User getUserById(long id);
    User getUserByName(String name);
}

