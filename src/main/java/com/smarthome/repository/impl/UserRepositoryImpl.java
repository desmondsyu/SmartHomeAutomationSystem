package com.smarthome.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthome.model.User;
import com.smarthome.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final String FILE_PATH = "src/main/resources/file/users.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<User> findAll() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public User save(User user) {
        List<User> users = findAll();
        users.add(user);
        writeToFile(users);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return findAll().stream().filter(user -> user.getUserId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public User update(User updatedUser) {
        List<User> users = findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(updatedUser.getUserId())) {
                users.set(i, updatedUser);
                writeToFile(users);
                return updatedUser;
            }
        }
        return null;
    }

    private void writeToFile(List<User> users) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
