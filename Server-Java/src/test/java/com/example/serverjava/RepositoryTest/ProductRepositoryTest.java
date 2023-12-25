package com.example.serverjava.RepositoryTest;


import com.example.serverjava.Entity.Product;
import com.example.serverjava.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    private Product product;


    @BeforeEach
    void init(){
        product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setDescription("test");
        product.setPrice(10);
    }

    @Test
    void testFindId() {

        productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(product.getId());

        assertEquals("test", foundProduct.get().getName());
    }

    @Test
    void testSaveNewProduct (){
        productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(product.getId());

        assertEquals("test" , foundProduct.get().getName());
        assertEquals("test", foundProduct.get().getDescription());
        assertEquals(10, foundProduct.get().getPrice());
    }

}
