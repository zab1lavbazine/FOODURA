package com.example.serverjava.Repository;

import com.example.serverjava.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    public Optional<Product> findById(UUID id);
    public List<Product> findAllById(UUID id);
}
