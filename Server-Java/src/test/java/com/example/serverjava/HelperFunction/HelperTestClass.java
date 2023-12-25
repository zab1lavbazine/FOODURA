package com.example.serverjava.HelperFunction;

import com.example.serverjava.Entity.Enum.Role;
import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Entity.User;

import java.util.List;
import java.util.Set;

public class HelperTestClass {
    public static Product createTestProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100);
        product.setDescription("Test Description");
        return product;
    }

    public static Order createTestOrder() {
        Order order = new Order();
        order.setAddress("Test Address");
        order.setNotion("Test Notion");
        order.setProducts(List.of(createTestProduct()));
        return order;
    }

    public static User createTestUser() {
        User user = new User();
        user.setUsername("Test Username");
        user.setPassword("Test Password");
        user.setRoles(Set.of(Role.USER));
        return user;
    }
}
