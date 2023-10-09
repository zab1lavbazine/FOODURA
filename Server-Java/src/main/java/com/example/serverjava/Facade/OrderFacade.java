package com.example.serverjava.Facade;


import com.example.serverjava.DTO.OrderINFO;
import com.example.serverjava.DTO.ProductINFO;
import com.example.serverjava.DTO.UserINFO;
import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.OrderWithProductsRequest;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Entity.User;
import com.example.serverjava.Service.OrderService;
import com.example.serverjava.Service.ProductService;
import com.example.serverjava.Service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;


    @Transactional
    public void createNewOrder(OrderWithProductsRequest request) throws Exception {
        Order order = new Order();
        User user = userService.getUserById(request.getUserId());
        order.setUser(user);
        userService.addOrder( order.getUser() ,order);
        List<Product> productList = productService.getAllProductsById(request.getProductIds());
        order.setProducts(productList);
        orderService.addNewOrder(order);
    }

    public void editOrder(UUID id, OrderWithProductsRequest request) {
        Order order = orderService.getOrderById(id);
        User user = userService.getUserById(request.getUserId());
        order.setUser(user);
        order.setNotion(request.getNotion());
        order.setProducts(productService.getAllProductsById(request.getProductIds()));
        orderService.updateOrder(order);
    }


    public void deleteOrder(UUID id) {
        Order order = orderService.getOrderById(id);
        User user = order.getUser();
        userService.deleteOrderFromUser(order);
        order.setUser(null);
        productService.deleteOrderFromProducts(order);
        order.setProducts(null);
        orderService.deleteById(id);
    }

    public List<OrderINFO> getAllOrdersDTO() throws IOException {
        List<Order> orderList = orderService.getAllOrders();
        List<OrderINFO> orderDTOList = new ArrayList<>();


        for (Order order : orderList){
            OrderINFO orderDTO = new OrderINFO(order.getId(), order.getNotion());

            UserINFO userDTO = userService.getUserDTO(order.getUser().getId());
            orderDTO.setUser(userDTO);

            List<ProductINFO> productINFOList = productService.getAllProductsDTO(order.getProducts());

            orderDTO.setProducts(productINFOList);
            orderDTOList.add(orderDTO);

        }
        return orderDTOList;
    }
}
