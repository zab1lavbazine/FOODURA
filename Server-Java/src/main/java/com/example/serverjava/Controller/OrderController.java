package com.example.serverjava.Controller;



import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.OrderINFO;
import com.example.serverjava.Entity.OrderWithProductsRequest;
import com.example.serverjava.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private final OrderService orderService;


    @GetMapping("/all")
    public ResponseEntity<List<OrderINFO>> getAllOrders(){
        List<OrderINFO> orderINFO = orderService.getAllOrdersINFO();
        return ResponseEntity.ok(orderINFO);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addNewOrder( @RequestBody OrderWithProductsRequest request){
        try {
            orderService.addNewOrder(request);
            return ResponseEntity.ok ("New order is added");
        } catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user");
        }
    }


    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editOrder (@RequestBody Order order, @PathVariable UUID id){
        Optional<Order> oldOrder = orderService.getById(id);
        if (oldOrder.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Not found");
        }

        Order updateOrder = oldOrder.get();
        updateOrder.setNotion(order.getNotion());
        orderService.updateOrder(updateOrder);
        return ResponseEntity.ok("Order updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById( @PathVariable UUID id){
        orderService.deleteById(id);
        return ResponseEntity.ok("Order deleted");
    }
}
