package com.example.serverjava.ServiceTests;


import com.example.serverjava.Entity.Order;
import com.example.serverjava.HelperFunction.HelperTestClass;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;


    @Test
    void testGetOrderById(){
        Order order = HelperTestClass.createTestOrder();

        when(orderRepository.findById(order.getId())).thenReturn(java.util.Optional.of(order));
        Order foundOrder = orderService.getOrderById(order.getId());
        assertNotNull(foundOrder);

        assertEquals(order.getAddress(), foundOrder.getAddress());
    }

    @Test
    void testGetOrderByUserId(){
        Order order = HelperTestClass.createTestOrder();

        when(orderRepository.getOrderByUserId(1L)).thenReturn(java.util.Optional.of(order));
        Order foundOrder = orderService.getOrderByUserId(1L);
        assertNotNull(foundOrder);

        assertEquals(order.getAddress(), foundOrder.getAddress());
    }

}

