package com.example.demoAuth.service;

import com.example.demoAuth.entity.Category;
import com.example.demoAuth.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category getCategory(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void updateCategory(String id, @Valid Category categoryDetails) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        category.setName(categoryDetails.getName());

        categoryRepository.save(category);
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
