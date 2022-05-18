package com.example.confectioneryApp.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public void create(Category category){
        categoryRepository.save(category);
    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    public Category getById(Long id){
        return findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Категории товара с таким id не существует"));
    }
}
