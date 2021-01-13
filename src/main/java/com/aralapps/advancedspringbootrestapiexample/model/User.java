package com.aralapps.advancedspringbootrestapiexample.model;

import java.util.UUID;

public class User {

    private final UUID userId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer age;
    private final Gender gender;

    public User(UUID userId, String firstName, String lastName, String email, Integer age, Gender gender) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    public enum Gender {
        MALE,
        FEMALE,
        UNSPECIFIED
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    public UUID getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}
