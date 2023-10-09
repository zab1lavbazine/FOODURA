package com.example.serverjava.Controller;



import com.example.serverjava.Entity.Order;
import com.example.serverjava.DTO.OrderINFO;
import com.example.serverjava.Entity.OrderWithProductsRequest;
import com.example.serverjava.Facade.OrderFacade;
import com.example.serverjava.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderFacade orderFacade;

    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<List<OrderINFO>> getAllOrders(){
        try {
            List<OrderINFO> orderINFO = orderFacade.getAllOrdersDTO();
            return ResponseEntity.ok(orderINFO);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @PostMapping
    public ResponseEntity<String> addNewOrder( @RequestBody OrderWithProductsRequest request){
        try {
            orderFacade.createNewOrder(request);
            return ResponseEntity.ok ("New order is added");
        } catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user");
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById( @PathVariable UUID id){
        orderFacade.deleteOrder(id);
        return ResponseEntity.ok("Order deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable UUID id){
        Order order = orderService.getOrderById(id);
        if (order != null){
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editOrder(@PathVariable UUID id, @RequestBody OrderWithProductsRequest request){
        orderFacade.editOrder(id, request);
        return ResponseEntity.ok("Notion of the product have been changed");
    }
}
