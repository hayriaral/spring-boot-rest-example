package com.aralapps.advancedspringbootrestapiexample.service;

import com.aralapps.advancedspringbootrestapiexample.dao.UserDao;
import com.aralapps.advancedspringbootrestapiexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> getUser(UUID userId) {
        return userDao.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public int updateUser(User user) {
        Optional<User> selectedUser = getUser(user.getUserId());
        if (selectedUser.isPresent()) {
            userDao.updateUser(user);
            return 1;
        }
        return -1;
    }

    public int removeUser(UUID userId) {
        Optional<User> selectedUser = getUser(userId);
        if (selectedUser.isPresent()) {
            userDao.deleteUser(userId);
            return 1;
        }
        return -1;
    }

    public int insertUser(User user) {
        return userDao.insertUser(UUID.randomUUID(), user);
    }
}
