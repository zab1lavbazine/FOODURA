package com.example.serverjava.Service;

import com.example.serverjava.DTO.UserINFO;
import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Enum.Role;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public List<UserINFO> getAllUsersDTO() {
        log.info("Getting all users from the database");
        List<User> userList = userRepository.findAll();
        return UserINFO.from(userList);
    }

    public User getUserById(Long id) {
        log.info("Find user with id: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public boolean editUser(User user, Long id) {
        User userFromDB = getUserById(id);
        if (userFromDB == null) return false;
        userFromDB.setUsername(user.getUsername());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setPhoneNumber(user.getPhoneNumber());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userFromDB.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userFromDB);
        log.info("user is updated id : {}", user.getId());
        return true;
    }

    public void addOrder(User user, Order order) {
        user.getOrderList().add(order);
        userRepository.save(user);
    }


    public boolean deleteUserById(Long id) {
        User user = getUserById(id);
        if (user == null) return false;
        userRepository.delete(user);
        log.info("user is deleted id : {}", user.getId());
        return true;
    }

    public boolean saveNewUser(User user) {
        String email = user.getEmail();
        //check if user with this email already exists
        if (userRepository.findByEmail(email).isPresent()) return false;
        //

        user.setActive(true);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        userRepository.save(user);
        log.info("new user is saved id : {}", user.getId());
        return true;
    }

    public User getUserByEmail(String email) {
        log.info("Find user with email : {}", email);
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean saveNewAdmin(User user) {
        String email = user.getEmail();

        if (userRepository.findByEmail(email).isPresent()) return false;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ADMIN);

        userRepository.save(user);
        log.info("new admin is saved id : {}", user.getId());
        return true;
    }

    public UserINFO getUserDTO(Long id) {
        Optional<User> user = Optional.ofNullable(getUserById(id));
        return user.map(UserINFO::new).orElse(null);
    }

    public void deleteOrderFromUser(Order order) {
        User user = order.getUser();
        user.getOrderList().remove(order);
        userRepository.save(user);
    }

    public boolean saveNewUserFromDTO(UserINFO user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setPassword(user.getPassword());
        return saveNewUser(newUser);
    }

    public boolean saveNewAdminFromDTO(UserINFO user) {
        User newAdmin = new User();
        newAdmin.setUsername(user.getUsername());
        newAdmin.setEmail(user.getEmail());
        newAdmin.setPhoneNumber(user.getPhoneNumber());
        newAdmin.setPassword(user.getPassword());
        return saveNewAdmin(newAdmin);
    }
}
