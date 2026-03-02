package org.example;


import org.example.config.IntegrationTest;
import org.example.dto.UserRecord;
import org.example.user.UserController;
import org.example.user.UserDao;
import org.example.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.example.data.TestData.randomUser;
import static org.junit.jupiter.api.Assertions.*;


class UserControllerSpringBootTest extends IntegrationTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;


    private UserRecord newUser;


    @BeforeEach
    public void setup() {
        newUser = randomUser();
        userController.createUser(newUser);
    }


    @Test
    public void testUserSummary() {

        // data comes out of the database already mapped - no extra mapping step
        var users = userController.getUsers(PageRequest.of(0, 10));

        assertTrue(users.getTotalElements() >= 1);
    }


    @Test
    public void testRepoVsDao() {

        var daoUser = userDao.getUserDto(newUser.username());
        var repoUser = userRepository.findRecord(newUser.username());

        assertEquals(daoUser, repoUser);
    }

}
