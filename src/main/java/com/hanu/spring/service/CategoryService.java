package com.hanu.spring.service;

import com.hanu.spring.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    void create(Category category);

    void update(Category category);

    Category getDetail(Long id);

    void deleteCategory(Long id);
}
