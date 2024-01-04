package com.example.serverjava.Repository;

import com.example.serverjava.Entity.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<List<Product>> findAllByName(String name);

    public Product findByName(String productName);

}
