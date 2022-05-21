package com.example.confectioneryApp.order;

import com.example.confectioneryApp.cart.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;

public class OrderServiceTest {
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void create(){
        Cart cart = new Cart();
        Long id = 1L;
        Order createdOrder = Order.builder()
                .id(id)
                .name("name")
                .surname("surname")
                .address("address")
                .additionalAddress("additionalAddress")
                .additionalInfo("additionalInfo")
                .city("city")
                .email("email")
                .phone("phone")
                .cart(cart)
                .build();
        orderService.create(createdOrder);
        Mockito.when(orderRepository.findByCart(cart)).thenReturn(Optional.ofNullable(createdOrder));
        Order createdRealOrder = orderService.findByCart(cart).get();
        Assertions.assertEquals(createdRealOrder, createdOrder);
    }

    @Test
    void findByCart(){
        Cart cart = new Cart();
        Long id = 1L;
        Order createdOrder = Order.builder()
                .id(id)
                .name("name")
                .surname("surname")
                .address("address")
                .additionalAddress("additionalAddress")
                .additionalInfo("additionalInfo")
                .city("city")
                .email("email")
                .phone("phone")
                .cart(cart)
                .build();
        Mockito.when(orderRepository.findByCart(cart)).thenReturn(Optional.ofNullable(createdOrder));
        Order createdRealOrder = orderService.findByCart(cart).get();
        Assertions.assertEquals(createdRealOrder, createdOrder);
    }
}
