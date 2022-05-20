package com.example.confectioneryApp.review;

import com.example.confectioneryApp.cart.Cart;
import com.example.confectioneryApp.product.Product;
import com.example.confectioneryApp.product.ProductRepository;
import com.example.confectioneryApp.product.ProductService;
import com.example.confectioneryApp.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;

public class ReviewServiceTest {
    private ReviewRepository reviewRepository;
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        reviewRepository = Mockito.mock(ReviewRepository.class);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    void create(){
        Long id = 1L;
        Review createdReview = Review.builder()
                .id(id)
                .user(new User())
                .product(new Product())
                .reviewText("reviewText")
                .build();
        reviewService.create(createdReview);
        Mockito.when(reviewRepository.getById(id)).thenReturn(createdReview);
        Review realCreatedReview = reviewService.getById(id);
        Assertions.assertEquals(realCreatedReview, createdReview);
    }

    @Test
    void getById(){
        Long id = 1L;
        Review createdReview = Review.builder()
                .id(id)
                .user(new User())
                .product(new Product())
                .reviewText("reviewText")
                .build();
        Mockito.when(reviewRepository.getById(id)).thenReturn(createdReview);
        Review realCreatedReview = reviewService.getById(id);
        Assertions.assertEquals(realCreatedReview, createdReview);
    }
}
