package com.example.serverjava.Service;

import com.example.serverjava.Entity.*;
import com.example.serverjava.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;


    @Transactional
    public void addNewOrder(Order order) {
        log.info("Saving new order {} to the database", order.getId());
        orderRepository.save(order);
    }


    public void updateOrder(Order order) {
        log.info("Updating order {} to the database", order.getId());
        orderRepository.save(order);
    }

    public void deleteById(UUID id) {
        log.info("Deleting order with id {}", id);
        orderRepository.deleteById(id);
    }


    public List<Order> getAllOrders() {
        log.info("Getting all orders from the database");
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        log.info("Getting order with id {} from the database", id);
        Optional<Order> order = orderRepository.getOrderById(id);
        return order.orElse(null);
    }

    public Order getOrderByUserId(UUID userId) {
        log.info("Getting order with user id {} from the database", userId);
        Optional<Order> order = orderRepository.getOrderByUserId(userId);
        return order.orElse(null);
    }

    public void deleteProductByIdFromOrder(UUID productId, UUID orderId) {
        log.info("Deleting product with id {} from order {}", productId, orderId);
        Order order = getOrderById(orderId);
        order.getProducts().removeIf(product -> product.getId().equals(productId));
        updateOrder(order);
    }
}
