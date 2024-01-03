package com.example.serverjava.Facade;

import com.example.serverjava.DTO.UserINFO;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Repository.UserRepository;
import com.example.serverjava.Service.OrderService;
import com.example.serverjava.Service.ProductService;
import com.example.serverjava.Service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public List<UserINFO> findAllUsersContainingProductInOrder(Long productId) {
        Product product = productService.getProductById(productId);
        if (product == null)
            return null;
        List<User> userList = userService.getAllUsersContainingProductInOrder(product);
        // make UserInfo from User
        List<UserINFO> userINFOList = UserINFO.from(userList);
        return userINFOList;
    }

}
