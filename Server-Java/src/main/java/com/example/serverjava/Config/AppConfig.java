package com.example.serverjava.Config;


import com.example.serverjava.Facade.OrderFacade;
import com.example.serverjava.Facade.ProductFacade;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Repository.ProductRepository;
import com.example.serverjava.Repository.UserRepository;
import com.example.serverjava.Service.OrderCleanUpService;
import com.example.serverjava.Service.OrderService;
import com.example.serverjava.Service.ProductService;
import com.example.serverjava.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
//    @Bean
//    public ProductService productService(ProductRepository productRepository){
//        return new ProductService(productRepository);
//    }
//
//    @Bean
//    public UserService userService(UserRepository userRepository){
//        return new UserService(userRepository);
//    }
//
//    @Bean
//    public OrderService orderService( OrderRepository orderRepository){
//        return new OrderService(orderRepository);
//    }
//
//    @Bean
//    public OrderFacade orderFacade(OrderService orderService, ProductService productService, UserService userService){
//        return new OrderFacade(orderService, productService, userService);
//    }
//
//    @Bean
//    public ProductFacade productFacade (ProductService productService,
//                                        OrderRepository orderRepository,
//                                        OrderCleanUpService orderCleanUpService){
//        return new ProductFacade(orderRepository, orderCleanUpService, productService);
//    }

}
