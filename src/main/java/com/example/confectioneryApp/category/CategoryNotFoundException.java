package com.example.confectioneryApp.category;

public class CategoryNotFoundException extends IllegalArgumentException{
    public CategoryNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
