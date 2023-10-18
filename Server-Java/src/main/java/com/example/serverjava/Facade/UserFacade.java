package com.example.serverjava.Facade;


import com.example.serverjava.Entity.User;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Repository.UserRepository;
import com.example.serverjava.Service.OrderService;
import com.example.serverjava.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    private final OrderService orderService;


    public boolean deleteUserById(UUID id) {
        User user = userService.getUserById(id);
        if (user == null) return false;
        orderService.deleteOrderByUser(user);
        userService.deleteUserById(id);
        return true;
    }

}
