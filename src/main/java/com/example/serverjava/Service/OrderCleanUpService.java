package com.example.serverjava.Service;

import com.example.serverjava.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderCleanUpService {

    private final OrderRepository orderRepository;

    public void deletOrdersWithoutProducts(){
       orderRepository.deleteEmptyOrders();
    }
}
