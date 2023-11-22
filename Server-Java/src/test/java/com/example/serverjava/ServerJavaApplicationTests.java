package com.example.serverjava;

import com.example.serverjava.DTO.OrderINFO;
import com.example.serverjava.DTO.ProductINFO;
import com.example.serverjava.DTO.UserINFO;
import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Repository.ProductRepository;
import com.example.serverjava.Repository.UserRepository;
import com.example.serverjava.Service.ProductService;
import com.example.serverjava.Service.UserService;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import javax.management.relation.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ServerJavaApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetUserById() {
        // Mocking UserRepository behavior
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    public void addNewProduct() {

        Product newProduct = new Product();
        newProduct.setDescription("test");
        newProduct.setName("test");
        newProduct.setPrice(10);
        newProduct.setId(1L);

        when(productRepository.findById(newProduct.getId())).thenReturn(Optional.of(newProduct));

        Product foundProduct = productService.getProductById(newProduct.getId());

        assertEquals(newProduct, foundProduct);
    }

}
