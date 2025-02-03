package com.smarthome.repository;

import com.smarthome.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User save(User user);

    User findById(Integer id);

    User update(User user);
}
