package com.example.serverjava.RepositoryTest;

import com.example.serverjava.Entity.Enum.Role;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    private User user;

    @BeforeEach
    void init (){
        user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("test");
        user.setPhoneNumber("123123");
        user.setRoles(Set.of(Role.USER));
    }


    @Test
    void testFindUserByEmail() {
        userRepository.save(user);
        User foundUser = userRepository.findByEmail(user.getEmail()).get();
        assertEquals("test", foundUser.getUsername());
    }

    @Test
    void testFindUserById() {
        userRepository.save(user);
        User foundUser = userRepository.findById(user.getId()).get();
        assertEquals("test", foundUser.getUsername());
    }

}


