# Spring Boot REST Example

## Table of contents

1. [Introduction](#introduction)
2. [Subjects](#subjects)
3. [Getting started](#getting-started)
4. [Development](#development-part-1)
   1. [Create model and DAO layers](#create-model-and-dao-layers)
   2. [Create service layer](#create-service-layer)
5. [References](#references)

## Introduction



## Subjects

* Spring REST API
* Spring dependency injection
* N-tier architecture
* Unit testing
* Mockito

## Getting started

1. **File** > **New** > **Project**
2. Select **Spring Initializer**, then choose a **Project SDK**, and initializer service URL as **default**. Click **Next**.
3. Set the project configuration and click **Next**.
4. Mark **Jersey** and **Spring Web** dependencies though selecting  **Web** tab. Click **Next**.
5. Set **Project name** and **Project location**, then click **Finish**.

## Development Part 1

### Create model and DAO layers

1. Create an `User` model.

   ```java
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
       
       //Getters...
   }
   ```

2. Create a `UserDao` interface.

   ```java
   public interface UserDao {
   
       Optional<User> getUserById(UUID userId);
   
       List<User> getAllUsers();
   
       int updateUser(User user);
   
       int deleteUser(UUID userId);
   
       int insertUser(UUID userId, User user);
   }
   ```

3. Create a fake database to test the code.

   ```java
   public class FakeDataDao implements UserDao {
   
       private static Map<UUID, User> fakeDatabase;
   
       static {
           fakeDatabase = new HashMap<>();
           UUID userId = UUID.randomUUID();
           fakeDatabase.put(userId, new User(userId, "Hayri", "Aral", "email@example.com", 27, User.Gender.MALE));
       }
       
       //Overrided methods...
   }
   ```

4. Implement DAO interface for CRUD operations.

### Create service layer

1. Create `UserService` class.

   ```java
   public class UserService {
   
       public Optional<User> getUser(UUID userId) {
           return null;
       }
   
       public List<User> getAllUsers() {
           return null;
       }
   
       public int updateUser(User user) {
           return 1;
       }
   
       public int removeUser(UUID userId) {
           return 1;
       }
   
       public int insertUser(UUID userId, User user) {
           return 1;
       }
   }
   ```

2. Apply dependency injection to `FakeDataDao` and `UserService` classes.

   ```java
   @Repository
   public class FakeDataDao implements UserDao {
   	//...
   }
   ```

   ```java
   @Service
   public class UserService {
   
       private UserDao userDao;
   
       @Autowired
       public UserService(UserDao userDao) {
           this.userDao = userDao;
       }
       
       // Other methods...
   }
   ```

   * [@Repository](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Repository.html): Indicates that an annotated class is a "Repository", originally defined by Domain-Driven Design (Evans, 2003) as "a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects". 
   * [@Service](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html): Indicates that an annotated class is a "Service". This annotation serves as a specialization of [`@Component`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Component.html), allowing for implementation classes to be autodetected through classpath scanning.
   * [@Autowired](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html): Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities.

3. Apply business logic into service layer.

   ```java
   @Service
   public class UserService {
   
       //...
   
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
   ```


## References

* [Spring Boot For Software Engineers](https://www.udemy.com/share/101Bv2BEQSdlxTQ3Q=/)
* [Public API documentation for the Spring Framework](https://docs.spring.io/spring-framework/docs/current/javadoc-api/overview-summary.html)
