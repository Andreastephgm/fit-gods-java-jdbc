package com.sena.controller;

import com.sena.model.User;
import com.sena.service.UserService;

import java.util.List;

public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User saveUser(User user){
        return userService.save(user);
    }

    public List<User> findAllUsers(){
        return userService.findAll();
    }

    public User findUserById(Long id){
        return userService.findById(id);
    }

    public User updateUser(User user){
        return userService.update(user);
    }

    public void deleteById(Long id){
        userService.deleteById(id);
    }
}
