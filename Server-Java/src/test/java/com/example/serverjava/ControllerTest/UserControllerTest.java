package com.example.serverjava.ControllerTest;


import com.example.serverjava.Controller.UserController;
import com.example.serverjava.DTO.UserINFO;
import com.example.serverjava.Entity.Enum.Role;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Facade.UserFacade;
import com.example.serverjava.Repository.UserRepository;
import com.example.serverjava.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private UserFacade userFacade;


    @Test
    void testGetAllUsers() {
        UserINFO user = new UserINFO();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("test");
        user.setPhoneNumber("123123");

        // Configure the userService mock to return the test user when getAllUsersDTO is called
        Mockito.when(userService.getAllUsersDTO()).thenReturn(List.of(user));

        // Call the controller method
        ResponseEntity<?> users = userController.getAllUsers();

        // Verify that the controller returns the expected user list
        assertEquals(1, ((List<UserINFO>) users.getBody()).size());

        // Verify that the userService.getAllUsersDTO method was called once
        Mockito.verify(userService, Mockito.times(1)).getAllUsersDTO();
    }


    @Test
    void testGetUserByEmail(){
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("test");
        user.setPhoneNumber("123123");
        user.setRoles(Set.of(Role.USER));

        Mockito.when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
         ResponseEntity<?> foundUser = userController.getUserByEmail(user.getEmail());

         assertEquals(user, foundUser.getBody());

         Mockito.verify(userService, Mockito.times(1)).getUserByEmail(user.getEmail());
    }

}
