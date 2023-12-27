package com.example.serverjava.Facade;


import com.example.serverjava.DTO.OrderINFO;
import com.example.serverjava.DTO.ProductINFO;
import com.example.serverjava.DTO.UserINFO;
import com.example.serverjava.Entity.*;
import com.example.serverjava.Entity.Enum.Status;
import com.example.serverjava.Entity.SupportEntity.OrderWithProductsRequest;
import com.example.serverjava.Service.OrderService;
import com.example.serverjava.Service.ProductService;
import com.example.serverjava.Service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;


    @Transactional
    public void createNewOrder(OrderWithProductsRequest request) throws Exception {

        // checking for existing order
        Order existingOrder = checkForExistingOrderForUser(request.getUserId(), request.getProductIds());

        // getting add products from request
        List<Product> productList = productService.getAllProductsById(request.getProductIds());

        if (existingOrder != null) {
            existingOrder.getProducts().addAll(productList);
            orderService.updateOrder(existingOrder);
        } else {
            Order order = new Order();
            order.setNotion(request.getNotion());
            order.setAddress(request.getAddress());
            User user = userService.getUserById(request.getUserId());
            order.setUser(user);
            order.getStatuses().add(Status.PROCESSING);
            order.setProducts(productList);
            userService.addOrder(order.getUser(), order);
            orderService.addNewOrder(order);
        }
    }


    private Order checkForExistingOrderForUser(Long userId, List<Long> productsId) {
        return orderService.getOrderByUserId(userId);
    }

    public void editOrder(Long id, OrderWithProductsRequest request) {
        Order order = orderService.getOrderById(id);
        User user = userService.getUserById(request.getUserId());
        order.setUser(user);
        order.setAddress(request.getAddress());
        order.setNotion(request.getNotion());
        order.setProducts(productService.getAllProductsById(request.getProductIds()));
        orderService.updateOrder(order);
    }


    public void deleteOrder(Long id) {
        Order order = orderService.getOrderById(id);
        userService.deleteOrderFromUser(order);
        order.setUser(null);
        productService.deleteOrderFromProducts(order);
        order.setProducts(null);
        orderService.deleteById(id);
    }

    public List<OrderINFO> getAllOrdersDTO() throws IOException {
        List<Order> orderList = orderService.getAllOrders();
        List<OrderINFO> orderDTOList = new ArrayList<>();


        for (Order order : orderList) {
            OrderINFO orderDTO = new OrderINFO(order.getId(), order.getNotion(), order.getAddress());
            orderDTO.setStatuses(new ArrayList<>(order.getStatuses()));
            UserINFO userDTO = userService.getUserDTO(order.getUser().getId());
            orderDTO.setUser(userDTO);
            orderDTO.setAddress(order.getAddress());

            List<ProductINFO> productINFOList = productService.getAllProductsDTO(order.getProducts());

            orderDTO.setProducts(productINFOList);
            orderDTOList.add(orderDTO);

        }
        return orderDTOList;
    }

    public void deleteOrderFromProducts(Product product) {
        List<Order> orderList = product.getOrders();
        for (Order order : orderList) {
            List<Product> productList = order.getProducts();
            if (productList == null) continue;
            productList.removeIf(product1 -> product1.getId().equals(product.getId()));
            if (productList.isEmpty()) {
                orderService.deleteOrderByUser(order.getUser());
            } else {
                order.setProducts(productList);
                orderService.updateOrder(order);
            }
        }
    }
}
