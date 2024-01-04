package com.example.serverjava.Repository;

import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("delete from Order o where o.products is empty")
    void deleteEmptyOrders();

    Optional<Order> getOrderByUserId(Long userId);

    List<Order> findAllByProductsContaining(Product product);

}
