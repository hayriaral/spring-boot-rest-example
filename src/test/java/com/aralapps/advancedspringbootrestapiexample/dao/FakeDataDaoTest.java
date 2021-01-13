package com.aralapps.advancedspringbootrestapiexample.dao;

import com.aralapps.advancedspringbootrestapiexample.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @BeforeEach
    void setUp() {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    void getUserById() {
        UUID idJonSnow = UUID.randomUUID();
        User userJonSnow = new User(idJonSnow, "Jon", "Snow", "jonsnow@mail.com", 30, User.Gender.MALE);
        fakeDataDao.insertUser(idJonSnow, userJonSnow);

        assertThat(fakeDataDao.getAllUsers()).hasSize(2);

        Optional<User> selectedUser = fakeDataDao.getUserById(idJonSnow);
        assertThat(selectedUser.isPresent()).isTrue();
        assertThat(selectedUser.get()).isEqualTo(userJonSnow);
    }

    @Test
    void shouldNotGetUserByRandomId() {
        Optional<User> user = fakeDataDao.getUserById(UUID.randomUUID());
        assertThat(user.isPresent()).isFalse();
    }

    @Test
    void getAllUsers() {
        List<User> users = fakeDataDao.getAllUsers();
        User user = users.get(0);

        assertThat(users).hasSize(1);
        assertThat(user.getFirstName()).isEqualTo("Hayri");
        assertThat(user.getLastName()).isEqualTo("Aral");
        assertThat(user.getEmail()).isEqualTo("email@example.com");
        assertThat(user.getAge()).isEqualTo(27);
        assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
        assertThat(user.getUserId()).isNotNull();
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void insertUser() {
    }
}