package com.example.serverjava.ControllerTest;

import com.example.serverjava.Controller.OrderController;
import com.example.serverjava.DTO.OrderINFO;
import com.example.serverjava.DTO.ProductINFO;
import com.example.serverjava.DTO.UserINFO;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Facade.OrderFacade;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Service.OrderService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderFacade orderFacade;


    private OrderINFO order;


    @BeforeEach
    void init() {
        order = new OrderINFO();
        order.setId(1L);
        order.setNotion("test");
        order.setAddress("test");


        UserINFO user = new UserINFO();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("test");
        user.setPhoneNumber("123123");
        order.setUser(user);

        ProductINFO product = new ProductINFO();
        product.setId(1L);
        product.setName("test");
        product.setDescription("test");
        product.setPrice(10);
        order.setProducts(List.of(product));

    }

    @Test
    void testGetAllOrders() {
        try {
            Mockito.when(orderFacade.getAllOrdersDTO()).thenReturn(List.of(order));

            ResponseEntity<?> orders = orderController.getAllOrders();
            assertEquals(1, ((List<OrderINFO>) orders.getBody()).size());

            Mockito.verify(orderFacade, Mockito.times(1)).getAllOrdersDTO();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
