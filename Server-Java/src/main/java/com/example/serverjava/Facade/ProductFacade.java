package com.example.serverjava.Facade;


import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Service.OrderCleanUpService;
import com.example.serverjava.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final OrderRepository orderRepository;

    private final OrderCleanUpService orderCleanUpService;

    private final ProductService productService;



    @Transactional
    public boolean deleteProductById(UUID id){
        Product product = productService.getProductById(id);
        if (product == null) return false;

        List<Order> orders = orderRepository.findByProductsContaining(product);
        for (Order order : orders){
            order.getProducts().remove(product);
        }
        orderCleanUpService.deletOrdersWithoutProducts();
        //check if there will be orders without products
        orderRepository.saveAll(orders);
        productService.deleteById(id);
        return true;
    }

}
