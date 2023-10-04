package com.example.serverjava.Controller;


import com.example.serverjava.Entity.User;
import com.example.serverjava.Entity.UserLoginData;
import com.example.serverjava.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() throws IOException {
        List<User> users = userService.getAllUsers();
        if (users != null){
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body ("list is empty");
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        Optional<User> user = userService.getByEmail(email);
        if (user.isPresent()){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with email ${email} is not found");
        }
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id){
        Optional<User> user = userService.getById(id);
        return user.orElse(null);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewUser (@RequestBody User user){
       boolean check = userService.saveNewUser(user);
       if (!check) {
           return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body("User with email ${user.getEmail()} is already exist");
       } else {
           return ResponseEntity.ok("New user is added");
       }
    }

    @PostMapping ("/admin/add")
    public ResponseEntity<?> addNewAdmin(@RequestBody User user){
        boolean check = userService.saveNewAdmin(user);
        if (!check){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with email ${user.getEmail()} is already exist");
        } else {
            return ResponseEntity.ok("New admin is added");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginData userLoginData){
        Optional<User> user = userService.getByEmail(userLoginData.getEmail());
        String password = userLoginData.getPassword();
        if (user.isPresent()){
            User user1 = user.get();
            if (passwordEncoder.matches(password, user1.getPassword())){
                return ResponseEntity.ok(user1);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }



    @PostMapping("/edit/{id}")
    public ResponseEntity<String> editUser(@RequestBody User user, @PathVariable UUID id){
        Optional<User> oldUser = userService.getById(id);

        if (oldUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
        User newUser = oldUser.get();
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPhoneNumber(user.getPhoneNumber());

        userService.saveNewUser(newUser);
        return ResponseEntity.ok("Edit user: ${id}");
    }


}
