package com.example.serverjava.FacadeTest;

import com.example.serverjava.DTO.OrderINFO;
import com.example.serverjava.Entity.Order;
import com.example.serverjava.Facade.OrderFacade;
import com.example.serverjava.HelperFunction.HelperTestClass;
import com.example.serverjava.Service.OrderService;
import com.example.serverjava.Service.ProductService;
import com.example.serverjava.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderFacadeTest {

    @Autowired
    private OrderFacade orderFacade;

    @MockBean
    private UserService userService;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderService orderService;


    @Test
void testGetAllOrdersDTO() {
        Order order = HelperTestClass.createTestOrder();
        when(orderService.getAllOrders()).thenReturn(List.of(order));
        when(userService.getUserById(order.getUser().getId())).thenReturn(order.getUser());
        when(productService.getAllProductsById(List.of(order.getProducts().get(0).getId()))).thenReturn(List.of(order.getProducts().get(0)));

        List<OrderINFO> orders = null;
        try {
            orders = orderFacade.getAllOrdersDTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        OrderINFO orderINFO = new OrderINFO(order);

        assert orders != null;
        assertEquals(1, orders.size());
        assertEquals(orderINFO.getId(), orders.get(0).getId());
        assertEquals(orderINFO.getNotion(), orders.get(0).getNotion());
        assertEquals(orderINFO.getAddress(), orders.get(0).getAddress());
    }
}
