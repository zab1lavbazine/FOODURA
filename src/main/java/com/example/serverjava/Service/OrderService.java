package com.example.serverjava.Service;

import com.example.serverjava.Entity.*;
import com.example.serverjava.Entity.Enum.Status;
import com.example.serverjava.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        order.getStatuses().add(Status.UPDATED);
        orderRepository.save(order);
    }

    public void deleteById(Long id) {
        log.info("Deleting order with id {}", id);
        orderRepository.deleteById(id);
    }

    public List<Order> getAllOrders() {
        log.info("Getting all orders from the database");
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        log.info("Getting order with id {} from the database", id);
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public Order getOrderByUserId(Long userId) {
        log.info("Getting order with user id {} from the database", userId);
        Optional<Order> order = orderRepository.getOrderByUserId(userId);
        return order.orElse(null);
    }

    public void deleteProductByIdFromOrder(Long productId, Long orderId) {
        log.info("Deleting product with id {} from order {}", productId, orderId);
        Order order = getOrderById(orderId);
        order.getProducts().removeIf(product -> product.getId().equals(productId));
        updateOrder(order);
    }

    public void deleteOrderByUser(User user) {
        log.info("Deleting order with user {}", user.getId());
        Order order = getOrderByUserId(user.getId());
        if (order == null)
            return;

        // make orders products null
        order.setProducts(null);
        updateOrder(order);
        orderRepository.delete(order);
    }

    public List<Order> getAllOrdersContainingProduct(Product product) {
        return orderRepository.findAllByProductsContaining(product);
    }
}
