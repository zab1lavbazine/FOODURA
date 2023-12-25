package com.example.serverjava.ControllerTest;


import com.example.serverjava.Controller.ProductController;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Repository.ProductRepository;
import com.example.serverjava.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.postgresql.hostchooser.HostRequirement.any;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;





    @Test
    void testGetAllProducts() {
        Mockito.when(productService.getAllProducts()).thenReturn(List.of());

        ResponseEntity<?> products = productController.getAllProducts();
        assertEquals(0, ((List<Product>) products.getBody()).size());

        Mockito.verify(productService, Mockito.times(1)).getAllProducts();

    }

    @Test
    void testAddNewProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setDescription("test");
        product.setPrice(10);


        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        Mockito.doNothing().when(productService).addProduct(productArgumentCaptor.capture());

        ResponseEntity<?> response = productController.addNewProduct(product);


        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, productArgumentCaptor.getValue());

        Mockito.verify(productService, Mockito.times(1)).addProduct(any(Product.class));

    }


    @Test
    void testFindById(){
        Product product = new Product();
        product.setId(1L);
        
    }
}
