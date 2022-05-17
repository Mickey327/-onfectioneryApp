package com.example.confectioneryApp.product;

public class ProductNotFoundException extends IllegalArgumentException{
    public ProductNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
