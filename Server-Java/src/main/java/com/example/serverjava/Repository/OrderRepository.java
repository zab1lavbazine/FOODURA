package com.example.serverjava.Repository;


import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Optional<Order> getOrderById(Long id);

    public List<Order> findByProductsContaining(Product product);


    @Modifying
    @Query("delete from Order o where o.products is empty")
    void deleteEmptyOrders();

    Optional<Order> getOrderByUserId(Long userId);

}
