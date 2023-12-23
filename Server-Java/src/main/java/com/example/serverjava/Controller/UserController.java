package com.example.serverjava.Controller;

import com.example.serverjava.DTO.UserINFO;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Entity.SupportEntity.UserLoginData;
import com.example.serverjava.Facade.UserFacade;
import com.example.serverjava.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final UserFacade userFacade;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserINFO> users = userService.getAllUsersDTO();
        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("list is empty");
        }
    }

    @GetMapping("/detailed")
    public ResponseEntity<?> getAllUsersDetailed() {
        List<User> users = userService.getAllUsers();
        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("list is empty");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        boolean checkDelete = userFacade.deleteUserById(id);
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
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody UserINFO user) {
        boolean check = userService.saveNewUserFromDTO(user);
        if (!check) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with email ${user.getEmail()} is already exist");
        } else {
            return ResponseEntity.ok("New user is added");
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<?> addNewAdmin(@RequestBody UserINFO user) {
        boolean check = userService.saveNewAdminFromDTO(user);
        if (!check) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with email ${user.getEmail()} is already exist");
        } else {
            return ResponseEntity.ok("New admin is added");
        }
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAllAdmins() {
        List<User> admins = userService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginData userLoginData) {
        User user = userService.getUserByEmail(userLoginData.getEmail());
        String password = userLoginData.getPassword();
        if (user != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Wrong password or email");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editUser(@RequestBody UserINFO userDTO, @PathVariable Long id) {
        boolean checkEdit = userService.editUserWithDTO(userDTO, id);
        if (!checkEdit) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        } else {
            return ResponseEntity.ok("User is updated");
        }
    }

}
