package com.example.confectioneryApp.UIcontrollers;

import com.example.confectioneryApp.product.Product;
import com.example.confectioneryApp.product.ProductService;
import com.example.confectioneryApp.review.Review;
import com.example.confectioneryApp.review.ReviewService;
import com.example.confectioneryApp.user.AppUserDetailsService;
import com.example.confectioneryApp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ProductService productService;
    private final AppUserDetailsService appUserDetailsService;
    private final ReviewService reviewService;

    @PostMapping("/review/{productId}")
    public String postReview(@ModelAttribute("review") Review review,
                             @AuthenticationPrincipal User user,
                             @PathVariable("productId") Long productId){
        Product product = productService.findByIdFetch(productId);
        if (user.getReviewList() == null){
            user.setReviewList(new ArrayList<>());
        }
        List<Review> reviewList = appUserDetailsService.findByIdFetch(user.getId()).getReviewList();
        Optional<Review> reviewOptional = reviewList.stream()
                .filter(o -> o.getProduct().equals(product)).findFirst();
        if (reviewOptional.isPresent()){
            Review reviewToChange = reviewOptional.get();
            reviewToChange.setReviewText(review.getReviewText());
            reviewService.create(reviewToChange);
        }
        else{
            review.setProduct(product);
            review.setUser(user);
            reviewService.create(review);
            reviewList.add(review);
            product.getReviewList().add(review);
            appUserDetailsService.save(user);
            productService.create(product);
        }
        return "redirect:/shop/product/{productId}";
    }
}
