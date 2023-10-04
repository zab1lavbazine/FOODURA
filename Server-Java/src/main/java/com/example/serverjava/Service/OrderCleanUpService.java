package com.example.serverjava.Service;

import com.example.serverjava.Entity.Order;
import com.example.serverjava.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCleanUpService {
    @Autowired
    private final OrderRepository orderRepository;

    public void deletOrdersWithoutProducts(){
       orderRepository.deleteEmptyOrders();
    }
}
