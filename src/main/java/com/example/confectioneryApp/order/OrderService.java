package com.example.confectioneryApp.order;

import com.example.confectioneryApp.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void create(Order order){
        orderRepository.save(order);
    }

    public Optional<Order> findByCart(Cart cart){
        return orderRepository.findByCart(cart);
    }
}
