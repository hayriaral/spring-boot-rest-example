package com.aralapps.advancedspringbootrestapiexample.dao;

import com.aralapps.advancedspringbootrestapiexample.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeDataDao implements UserDao {

    private Map<UUID, User> fakeDatabase;

    public FakeDataDao() {
        fakeDatabase = new HashMap<>();
        UUID userId = UUID.randomUUID();
        fakeDatabase.put(userId, new User(userId, "Hayri", "Aral", "email@example.com", 27, User.Gender.MALE));
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        return Optional.ofNullable(fakeDatabase.get(userId));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(fakeDatabase.values());
    }

    @Override
    public int updateUser(User user) {
        fakeDatabase.put(user.getUserId(), user);
        return 1;
    }

    @Override
    public int deleteUser(UUID userId) {
        fakeDatabase.remove(userId);
        return 1;
    }

    @Override
    public int insertUser(UUID userId, User user) {
        fakeDatabase.put(userId, user);
        return 1;
    }
}
