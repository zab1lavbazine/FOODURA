package com.example.serverjava.ServiceTests;


import com.example.serverjava.Entity.Product;
import com.example.serverjava.Repository.ProductRepository;
import com.example.serverjava.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void testGetProductById(){
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setDescription("test");
        product.setPrice(10);


        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        assertEquals("test", productService.getProductById(1L).getName());

    }

}

