package com.example.confectioneryApp.review;

import com.example.confectioneryApp.product.Product;
import com.example.confectioneryApp.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "review")
@Table
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "review_text")
    private String reviewText;
}
