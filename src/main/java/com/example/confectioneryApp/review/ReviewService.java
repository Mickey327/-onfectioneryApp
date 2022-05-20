package com.example.confectioneryApp.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void create(Review review){
        reviewRepository.save(review);
    }
    public Review getById(Long id){
        return reviewRepository.getById(id);
    }
}
