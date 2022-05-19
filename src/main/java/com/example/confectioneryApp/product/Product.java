package com.example.confectioneryApp.product;

import com.example.confectioneryApp.category.Category;
import com.example.confectioneryApp.review.Review;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product")
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "weight")
    private double weight;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Review> reviewList;
}
