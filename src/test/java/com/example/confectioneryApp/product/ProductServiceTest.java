package com.example.confectioneryApp.product;

import com.example.confectioneryApp.category.Category;
import com.example.confectioneryApp.category.CategoryNotFoundException;
import com.example.confectioneryApp.order.OrderRepository;
import com.example.confectioneryApp.order.OrderService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductServiceTest {
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void create(){
        Long id = 1L;
        Product createdProduct = Product.builder()
                .id(id)
                .name("name")
                .price(100)
                .weight(100)
                .imageName("imageName")
                .description("description")
                .category(new Category())
                .reviewList(new ArrayList<>())
                .build();
        productService.create(createdProduct);
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.ofNullable(createdProduct));
        Product createdRealProduct = productService.getById(id);
        Assertions.assertEquals(createdRealProduct, createdProduct);
    }

    @Test
    void findById(){
        Long id = 1L;
        Product createdProduct = Product.builder()
                .id(id)
                .name("name")
                .price(100)
                .weight(100)
                .imageName("imageName")
                .description("description")
                .category(new Category())
                .reviewList(new ArrayList<>())
                .build();
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.ofNullable(createdProduct));
        Product createdRealProduct = productService.getById(id);
        Assertions.assertEquals(createdRealProduct, createdProduct);
    }

    @Test
    void getByCategoryId(){
        Long id = 1L;
        Category category = Category.builder().id(id).name("name").build();
        Product createdProduct = Product.builder()
                .id(id)
                .name("name")
                .price(100)
                .weight(100)
                .imageName("imageName")
                .description("description")
                .category(category)
                .reviewList(new ArrayList<>())
                .build();
        Mockito.when(productRepository.findByCategoryId(id)).thenReturn(List.of(createdProduct));
        List<Product> createdRealProduct = productService.getByCategoryId(id);
        Assertions.assertEquals(createdRealProduct, List.of(createdProduct));
    }

    @Test
    void deleteById(){
        Long id = 1L;
        Product createdProduct = Product.builder()
                .id(id)
                .name("name")
                .price(100)
                .weight(100)
                .imageName("imageName")
                .description("description")
                .category(new Category())
                .reviewList(new ArrayList<>())
                .build();
        productService.create(createdProduct);
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.ofNullable(createdProduct));
        Product createdRealProduct = productService.getById(id);
        Assertions.assertEquals(createdRealProduct, createdProduct);
        productService.deleteById(id);
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(ProductNotFoundException.class ,() -> productService.getById(id));
    }
}
