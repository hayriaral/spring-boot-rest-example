package com.aralapps.advancedspringbootrestapiexample.dao;

import com.aralapps.advancedspringbootrestapiexample.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    Optional<User> getUserById(UUID userId);

    List<User> getAllUsers();

    int updateUser(User user);

    int deleteUser(UUID userId);

    int insertUser(UUID userId, User user);
}
