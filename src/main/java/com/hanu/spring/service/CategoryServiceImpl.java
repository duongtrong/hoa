package com.hanu.spring.service;

import com.hanu.spring.model.Category;
import com.hanu.spring.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void createOrUpdate(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getDetail(Long id) {
        return categoryRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
