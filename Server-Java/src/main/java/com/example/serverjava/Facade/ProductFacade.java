package com.example.serverjava.Facade;


import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Service.OrderCleanUpService;
import com.example.serverjava.Service.OrderService;
import com.example.serverjava.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final OrderFacade orderFacade;

    private final ProductService productService;



    @Transactional
    public boolean deleteProductById(UUID id){
        Product product = productService.getProductById(id);
        if (product == null) return false;
        orderFacade.deleteOrderFromProducts(product);
        productService.deleteById(id);
        return true;
    }

}
