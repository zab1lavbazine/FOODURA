package com.example.serverjava.ServiceTests;


import com.example.serverjava.Entity.User;
import com.example.serverjava.HelperFunction.HelperTestClass;
import com.example.serverjava.Repository.UserRepository;
import com.example.serverjava.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;



    @Test
    void testGetUserById(){
        User user = HelperTestClass.createTestUser();

        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
        User foundUser = userService.getUserById(user.getId());
        assertNotNull(foundUser);

        assertEquals(user.getUsername(), foundUser.getUsername());
    }

    @Test
    void testGetUserByEmail(){
        User user = HelperTestClass.createTestUser();

        when(userRepository.findByEmail(user.getEmail())).thenReturn(java.util.Optional.of(user));
        User foundUser = userService.getUserByEmail(user.getEmail());
        assertNotNull(foundUser);

        assertEquals(user.getUsername(), foundUser.getUsername());
    }


}
