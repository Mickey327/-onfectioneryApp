package com.example.confectioneryApp.review;

import com.example.confectioneryApp.product.Product;
import com.example.confectioneryApp.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
