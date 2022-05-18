package com.example.confectioneryApp.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void save(Cart cart){
        cartRepository.save(cart);
    }

    public Cart findByIdFetch(Long id){
        return cartRepository.findByIdFetch(id);
    }
}
