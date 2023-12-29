package com.example.serverjava.HelperFunction;

import com.example.serverjava.DTO.UserINFO;
import com.example.serverjava.Entity.Enum.Role;
import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Entity.SupportEntity.OrderWithProductsRequest;
import com.example.serverjava.Entity.User;

import java.util.List;
import java.util.Set;

public class HelperTestClass {
    public static Product createTestProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100);
        product.setDescription("Test Description");
        return product;
    }

    public static Order createTestOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setAddress("Test Address");
        order.setNotion("Test Notion");
        order.setProducts(List.of(createTestProduct()));
        order.setUser(createTestUser());
        return order;
    }

    public static User createTestUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Test Username");
        user.setPassword("Test Password");
        user.setRoles(Set.of(Role.USER));
        return user;
    }

    public static OrderWithProductsRequest createTestOrderWithProductsRequest() {
        OrderWithProductsRequest orderWithProductsRequest = new OrderWithProductsRequest();
        orderWithProductsRequest.setAddress("Test Address");
        orderWithProductsRequest.setNotion("Test Notion");
        orderWithProductsRequest.setProductIds(List.of(1L));
        orderWithProductsRequest.setUserId(1L);
        return orderWithProductsRequest;
    }

    public static UserINFO createTestUserINFO() {
        UserINFO user = new UserINFO();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("test");
        user.setPhoneNumber("123123");
        return user;
    }
}
