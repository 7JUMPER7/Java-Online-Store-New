package com.example.onlinestorenew.services;

import com.example.onlinestorenew.dao.UserDao;
import com.example.onlinestorenew.models.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {
    private UserDao userDao;
    public UserService() {
        userDao = new UserDao();
    }

    public UserEntity findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public boolean createUser(UserEntity user) {
        if(user.getRole() == null) {
            user.setRole("USER");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        return userDao.createUser(user);
    }
}
