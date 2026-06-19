package com.example.stock.service;


import com.example.stock.model.Category;
import com.example.stock.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private CategoryService categoryRepository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category save(Category category) {
        return repository.save(category);
    }
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
