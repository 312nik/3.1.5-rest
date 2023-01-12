package com.kata312.service;


import com.kata312.model.User;

import java.util.List;

public interface UserService {
    void updateUser(User user,String[] rolesSelected);
    void removeUserById(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
    void addUser(User user,String[] rolesSelected);
    User getUserByEmail(String email);
    String getRolesToString(User user);
}




