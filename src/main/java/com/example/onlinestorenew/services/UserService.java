package com.example.onlinestorenew.services;

import com.example.onlinestorenew.dao.UserDao;
import com.example.onlinestorenew.models.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserService {
    private UserDao userDao;
    public UserService() {
        userDao = new UserDao();
    }

    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    public UserEntity findById(Integer id) {
        return userDao.findById(id);
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

    public boolean updateUser(UserEntity user, String newPassword) {
        if(user.getRole() == null) {
            user.setRole("USER");
        }

        if(newPassword != null && !newPassword.equals("")) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(newPassword));
        }

        return userDao.updateUser(user);
    }
}
