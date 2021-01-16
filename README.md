# Spring Boot REST Example

![GitHub top language](https://img.shields.io/github/languages/top/hayriaral/spring-boot-rest-example?style=for-the-badge)	![Lines of code](https://img.shields.io/tokei/lines/github/hayriaral/spring-boot-rest-example?style=for-the-badge)	![GitHub repo size](https://img.shields.io/github/repo-size/hayriaral/spring-boot-rest-example)    ![GitHub last commit](https://img.shields.io/github/last-commit/hayriaral/spring-boot-rest-example?style=for-the-badge)	[![GitHub license](https://img.shields.io/github/license/hayriaral/spring-boot-rest-example?style=for-the-badge)](https://github.com/hayriaral/spring-boot-rest-example/blob/master/LICENSE)

## Table of contents

1. [Introduction](#1-introduction)
2. [Subjects](#2-subjects)
3. [Getting started](#3-getting-started)
4. [Development](#4-development)
   1. [Create model and DAO layers](#create-model-and-dao-layers)
   2. [Create service layer](#create-service-layer)
5. [Testing](#5-testing)
   1. [Unit test with JUnit](#unit-test-with-junit)
   2. [Mocking objects with Mockito](#mocking-objects-with-mockito)
6. [References](#6-references)

## 1. Introduction

Welcome to my own Java workspace. I created this repo to practice with Spring framework. I referred to the awesome Udemy course which named [Spring Boot For Software Engineers](https://www.udemy.com/share/101Bv2BEQSdlxTQ3Q=/) for this repo.

Please, feel free to use the repo, I hope it will help you somehow.

I will be appreciated any feedback that I received.

## 2. Subjects

* Spring REST API
* Spring dependency injection
* N-tier architecture
* Unit testing
* Mockito

## 3. Getting started

1. **File** > **New** > **Project**
2. Select **Spring Initializer**, then choose a **Project SDK**, and initializer service URL as **default**. Click **Next**.
3. Set the project configuration and click **Next**.
4. Mark **Jersey** and **Spring Web** dependencies though selecting  **Web** tab. Click **Next**.
5. Set **Project name** and **Project location**, then click **Finish**.

## 4. Development

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
   
       private Map<UUID, User> fakeDatabase;
   
       public FakeDataDao() {
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

## 5. Testing

### Unit test with JUnit

1. Create an unit test for `FakeDataDao` class through `Ctrl+Shift+T` shortcut when you are in the class. Select **JUnit5** testing library, mark **setUp/@Before**, and all members of the class. Click **OK**.

2. Declare a `FakeDataDao` object for the tests. Instantiate the object in `setUp()` method. This method will be invoked every single test.

   ```java
   class FakeDataDaoTest {
   
       private FakeDataDao fakeDataDao;
   
       @BeforeEach
       void setUp() {
           fakeDataDao = new FakeDataDao();
       }
       
       //Test methods...
   }
   ```

3. Complete the tests for `FakeDataDaoTest` class.

### Mocking objects with Mockito

1. Create an unit test for `UserService` class through `Ctrl+Shift+T` shortcut when you are in the class. Select **JUnit5** testing library, mark **setUp/@Before**, and all members of the class. Click **OK**.

2. Declare a `FakeDataDao` object with `@Mock` annotation and `UserService` object for the tests. Instantiate the object of `UserService` with mock in `setUp()` method. This method will be invoked every single test.

   ```java
   class UserServiceTest {
   
       @Mock
       private FakeDataDao fakeDataDao;
       private UserService userService;
   
       @BeforeEach
       void setUp() {
           MockitoAnnotations.openMocks(this);
           userService = new UserService(fakeDataDao);
       }
       
       //Test methods...
   }
   ```

3. Complete the tests for `UserServiceTest` class. `getAllUsers()` shown below as an example.

   ```java
   class UserServiceTest {
   
       //...
       
       @Test
       void getAllUsers() {
           UUID idJonSnow = UUID.randomUUID();
           User userJonSnow = new User(idJonSnow, "Jon", "Snow", "jonsnow@mail.com", 30, User.Gender.MALE);
           ImmutableList<User> users = new ImmutableList.Builder<User>()
                   .add(userJonSnow)
                   .build();
   
           given(fakeDataDao.getAllUsers()).willReturn(users);
           List<User> allUsers = userService.getAllUsers();
   
           assertThat(allUsers).hasSize(1);
   
           //Check if the data is correct.
           User user = allUsers.get(0);
   
           AssertionsForClassTypes.assertThat(user.getFirstName()).isEqualTo("Jon");
           AssertionsForClassTypes.assertThat(user.getLastName()).isEqualTo("Snow");
           AssertionsForClassTypes.assertThat(user.getEmail()).isEqualTo("jonsnow@mail.com");
           AssertionsForClassTypes.assertThat(user.getAge()).isEqualTo(30);
           AssertionsForInterfaceTypes.assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
           AssertionsForInterfaceTypes.assertThat(user.getUserId()).isNotNull();
       }
       
       //...
   }
   ```

4. Will be completed later...


## 6. References

* [Spring Boot For Software Engineers](https://www.udemy.com/share/101Bv2BEQSdlxTQ3Q=/)
* [Public API documentation for the Spring Framework](https://docs.spring.io/spring-framework/docs/current/javadoc-api/overview-summary.html)
