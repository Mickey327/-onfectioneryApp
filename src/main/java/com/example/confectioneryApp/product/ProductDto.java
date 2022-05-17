package com.example.confectioneryApp.product;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Double weight;
    private Double price;
    private String description;
    private String imageName;
    private Long categoryId;
}
