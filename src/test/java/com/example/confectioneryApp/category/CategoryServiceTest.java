package com.example.confectioneryApp.category;

import com.example.confectioneryApp.cart.Cart;
import com.example.confectioneryApp.cart.CartRepository;
import com.example.confectioneryApp.cart.CartService;
import com.example.confectioneryApp.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

public class CategoryServiceTest {
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void create(){
        Long id = 1L;
        Category createdCategory = Category.builder()
                .id(id)
                .name("name")
                .build();
        categoryService.create(createdCategory);
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.ofNullable(createdCategory));
        Category createdRealCategory = categoryService.getById(id);
        Assertions.assertEquals(createdRealCategory, createdCategory);
    }

    @Test
    void findById(){
        Long id = 1L;
        Category createdCategory = Category.builder()
                .id(id)
                .name("name")
                .build();
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.ofNullable(createdCategory));
        Category createdRealCategory = categoryService.findById(id).get();
        Assertions.assertEquals(createdRealCategory, createdCategory);
    }

    @Test
    void deleteById(){
        Long id = 1L;
        Category createdCategory = Category.builder()
                .id(id)
                .name("name")
                .build();
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.ofNullable(createdCategory));
        Category createdRealCategory = categoryService.findById(id).get();
        Assertions.assertEquals(createdRealCategory, createdCategory);
        categoryService.deleteById(id);
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(CategoryNotFoundException.class ,() -> categoryService.getById(id));
    }
}
