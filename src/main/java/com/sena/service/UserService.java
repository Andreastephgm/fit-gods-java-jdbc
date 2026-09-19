package com.sena.service;

import com.sena.model.User;
import com.sena.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user ){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id);
    }

    public User update(User user){
        return userRepository.update(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
