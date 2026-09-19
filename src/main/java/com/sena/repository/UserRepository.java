package com.sena.repository;

import com.sena.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);
    List<User> findAll();
    User findById(Long id);
    User update(User user);
    void deleteById(Long id);

}
