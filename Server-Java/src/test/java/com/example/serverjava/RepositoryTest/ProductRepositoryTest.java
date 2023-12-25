package com.example.serverjava.RepositoryTest;


import com.example.serverjava.Entity.Product;
import com.example.serverjava.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindId() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setDescription("test");
        product.setPrice(10);

        productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(1L);

        assertEquals("test", foundProduct.get().getName());
    }

}
