package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Long, User> db = new HashMap<>();

    @Override
    public User createUser(User user) {
        db.put(user.getId(), user);
        return user;
    }

    @Override
    public User getUser(Long id) {
        return db.get(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return db.values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(db.values());
    }

    @Override
    public void deleteUser(Long id) {
        db.remove(id);
    }
}
