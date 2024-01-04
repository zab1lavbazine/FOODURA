package com.example.serverjava.Facade;

import com.example.serverjava.DTO.UserINFO;
import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Service.OrderService;
import com.example.serverjava.Service.ProductService;
import com.example.serverjava.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    private final ProductService productService;

    private final OrderService orderService;

    public boolean deleteUserById(Long id) {
        User user = userService.getUserById(id);
        if (user == null)
            return false;
        orderService.deleteOrderByUser(user);
        userService.deleteUserById(id);
        return true;
    }

    public List<UserINFO> findAllUsersContainingProductInOrder(String productName) {
        Product product = productService.getProductByName(productName);
        List<Order> orders = orderService.getAllOrdersContainingProduct(product);
        log.info("all orders getted");
        List<User> users = new ArrayList<>();

        for (Order order : orders) {
            users.add(order.getUser());
        }

        return UserINFO.from(users);
    }

}
