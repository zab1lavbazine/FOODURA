package com.example.serverjava.Controller;

import com.example.serverjava.Entity.User;
import com.example.serverjava.Entity.UserLoginData;
import com.example.serverjava.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() throws IOException {
        List<User> users = userService.getAllUsers();
        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("list is empty");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        boolean checkDelete = userService.deleteUserById(id);
        if (checkDelete) {
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

    @PostMapping
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        boolean check = userService.saveNewUser(user);
        if (!check) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with email ${user.getEmail()} is already exist");
        } else {
            return ResponseEntity.ok("New user is added");
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<?> addNewAdmin(@RequestBody User user) {
        boolean check = userService.saveNewAdmin(user);
        if (!check) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with email ${user.getEmail()} is already exist");
        } else {
            return ResponseEntity.ok("New admin is added");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginData userLoginData) {
        User user = userService.getUserByEmail(userLoginData.getEmail());
        String password = userLoginData.getPassword();
        if (user != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editUser(@RequestBody User user, @PathVariable UUID id) {
        boolean checkEdit = userService.editUser(user, id);
        if (!checkEdit) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        } else {
            return ResponseEntity.ok("User is updated");
        }
    }

}
