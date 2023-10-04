package com.example.serverjava.Service;


import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final OrderCleanUpService orderCleanUpService;

    public List<Product> getAllProducts ()  {
        return productRepository.findAll();
    }

    public void addProduct (Product product){
        productRepository.save(product);
        log.info("adding new product : id {}", product.getId());
    }

    public Optional<Product> findById(UUID id ){
        return productRepository.findById(id);
    }


    @Transactional
    public void deleteById(UUID id){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();

           List<Order> orders = orderRepository.findByProductsContaining(product);
            for (Order order : orders){
                order.getProducts().remove(product);
            }
            orderCleanUpService.deletOrdersWithoutProducts();
            orderRepository.saveAll(orders);
            productRepository.deleteById(id);
        } else {
            log.info("product with id {} is not found", id);
        }

    }
}
