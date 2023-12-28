package com.example.serverjava.Config;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Entity.Enum.Role;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Repository.ProductRepository;
import com.example.serverjava.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdminConfig implements CommandLineRunner {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminConfig(UserRepository userRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository,
            ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> admin = userRepository.findByEmail("admin@gmail.com");
        if (!admin.isPresent()) {
            log.info("Creating admin");
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRoles(Set.of(Role.ADMIN));
            user.setActive(true);
            user.setUsername("admin");

            Product product = new Product();
            product.setName("product");
            product.setPrice(1000);
            product.setDescription("description");

            productRepository.save(product);
            userRepository.save(user);

            Order order = new Order();
            order.setProducts(List.of(product));
            order.setUser(user);
            order.setAddress("address");
            order.setNotion("notion");
            orderRepository.save(order);

        } else {
            log.info("Admin already exists");
        }
    }
}
