package com.example.serverjava.Facade;


import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

}
