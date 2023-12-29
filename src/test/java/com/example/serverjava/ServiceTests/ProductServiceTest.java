package com.example.serverjava.ServiceTests;


import com.example.serverjava.Entity.Product;
import com.example.serverjava.HelperFunction.HelperTestClass;
import com.example.serverjava.Repository.ProductRepository;
import com.example.serverjava.Service.ProductService;
import org.junit.jupiter.api.Test;
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
        Product product = HelperTestClass.createTestProduct();


        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        assertEquals(product.getName(), productService.getProductById(1L).getName());

    }

}

