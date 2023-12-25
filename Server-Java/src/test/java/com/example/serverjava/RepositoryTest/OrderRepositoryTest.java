package com.example.serverjava.RepositoryTest;


import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;


    private Order order;


    @BeforeEach
    void init (){

        order = new Order();
        order.setId(1L);
        order.setAddress("test");
        order.setNotion("test");
        order.setUser(null);

        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setDescription("test");
        product.setPrice(10);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("test2");
        product2.setDescription("test2");
        product2.setPrice(20);

        order.getProducts().add(product);
        order.getProducts().add(product2);

    }


    @Test
    void testGetOrderById() {

        orderRepository.save(order);

        Order foundOrder = orderRepository.getOrderById(order.getId()).get();

        assertEquals("test", foundOrder.getAddress());
        assertEquals("test", foundOrder.getNotion());
        assertEquals(2, foundOrder.getProducts().size());
    }




    @Test
    void testDeleteEmptyOrders() {

        // delete all products from order
        order.getProducts().clear();


        orderRepository.save(order);

        orderRepository.deleteEmptyOrders();

        assertEquals(0, orderRepository.findAll().size());
    }

}
