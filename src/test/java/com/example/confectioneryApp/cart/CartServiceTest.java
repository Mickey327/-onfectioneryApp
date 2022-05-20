package com.example.confectioneryApp.cart;

import com.example.confectioneryApp.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

public class CartServiceTest {
    private CartRepository cartRepository;
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        cartRepository = Mockito.mock(CartRepository.class);
        cartService = new CartService(cartRepository);
    }

    @Test
    void create(){
        Long id = 1L;
        Cart createdCart = Cart.builder()
                .id(id)
                .cartItemList(new ArrayList<>())
                .user(new User())
                .build();
        cartService.save(createdCart);
        Mockito.when(cartRepository.findByIdFetch(id)).thenReturn(createdCart);
        Cart createdRealCart = cartService.findByIdFetch(id);
        Assertions.assertEquals(createdRealCart, createdCart);
    }

    @Test
    void findByIdFetch(){
        Long id = 1L;
        Cart createdCart = Cart.builder()
                .id(id)
                .cartItemList(new ArrayList<>())
                .user(new User())
                .build();
        Mockito.when(cartRepository.findByIdFetch(id)).thenReturn(createdCart);
        Cart createdRealCart = cartService.findByIdFetch(id);
        Assertions.assertEquals(createdRealCart, createdCart);
    }
}
