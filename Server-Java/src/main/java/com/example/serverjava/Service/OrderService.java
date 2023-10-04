package com.example.serverjava.Service;

import com.example.serverjava.Entity.*;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Repository.ProductRepository;
import com.example.serverjava.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service    
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ProductRepository productRepository;



    @Transactional
    public void addNewOrder(OrderWithProductsRequest orderWithProductsRequest) {
        Order order = new Order();
        try {
            //setting notion
            order.setNotion(orderWithProductsRequest.getNotion());

            // setting user if exists
            Optional<User> user = userRepository.findById(orderWithProductsRequest.getUserId());
            if (user.isEmpty()) {
                throw new Exception("User not found");
            }
            order.setUser(user.get());

            // setting products if exists
            List<Product> products = new ArrayList<>();
            for (UUID productId : orderWithProductsRequest.getProductIds()) {
                Optional<Product> product = productRepository.findById(productId);
                if (product.isEmpty()) {
                    throw new Exception("Product not found");
                }
                products.add(product.get());
                //add order into product
                product.get().getOrders().add(order);
            }

            order.setProducts(products);
            orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<Order> getById (UUID id){
        return orderRepository.findById(id);
    }

    public void updateOrder(Order order){
        orderRepository.save(order);
    }

  public void deleteById(UUID id){
        orderRepository.deleteById(id);
  }



    public List<OrderINFO> getAllOrdersINFO() {
        List<Order> orders = orderRepository.findAll();
        List<OrderINFO> orderINFOS = new ArrayList<>();
        for (Order order : orders) {
            OrderINFO orderDTO = new OrderINFO();
            orderDTO.setId(order.getId());
            orderDTO.setNotion(order.getNotion());

            // Map User entity to UserDTO
            UserINFO userDTO = new UserINFO();
            userDTO.setId(order.getUser().getId());
            userDTO.setUsername(order.getUser().getUsername());
            userDTO.setEmail(order.getUser().getEmail());
            orderDTO.setUser(userDTO);

            // Map List of Product entities to List of ProductDTOs
            List<ProductINFO> productDTOs = new ArrayList<>();
            for (Product product : order.getProducts()) {
                ProductINFO productDTO = new ProductINFO();
                productDTO.setId(product.getId());
                productDTO.setName(product.getName());
                productDTO.setDescription(product.getDescription());
                productDTO.setPrice(product.getPrice());
                productDTOs.add(productDTO);
            }
            orderDTO.setProducts(productDTOs);

            orderINFOS.add(orderDTO);
        }

        return orderINFOS;
    }
}
