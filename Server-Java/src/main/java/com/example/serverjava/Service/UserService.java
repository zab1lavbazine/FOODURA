package com.example.serverjava.Service;

import com.example.serverjava.Entity.Role;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getById (UUID id){
        log.info("Find user with id: {}", id);
        return userRepository.findById(id);
    }

    public boolean saveNewUser(User user){
        String email = user.getEmail();

        if (userRepository.findByEmail(email).isPresent()) return false;

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);


        userRepository.save(user);
        log.info("new user is saved id : {}", user.getId());
        return true;
    }

    public Optional<User> getByEmail(String email) {
        log.info("Find user with email : {}", email);
        return userRepository.findByEmail(email);
    }

    public boolean saveNewAdmin(User user) {
        String email = user.getEmail();

        if (userRepository.findByEmail(email).isPresent()) return false;

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ADMIN);

        userRepository.save(user);
        log.info("new admin is saved id : {}", user.getId());
        return true;
    }
}
