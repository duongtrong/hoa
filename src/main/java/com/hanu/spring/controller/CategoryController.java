package com.hanu.spring.controller;

import com.hanu.spring.model.Category;
import com.hanu.spring.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categoryResponses", categories);
        return "category/data";
    }

    @GetMapping("/create")
    public String createO(Model model) {
        model.addAttribute("categoryRequest", new Category());
        return "category/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("categoryRequest") Category categoryRequest) {
        categoryService.create(categoryRequest);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getDetail(id);
        if (category == null) {
            return "Lỗi rồi";
        }
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/edit/{id}")
    public String editO(@ModelAttribute("category") @Validated Category category, @PathVariable("id") Long id, BindingResult result) {
        if (result.hasErrors()) {
            category.setId(id);
            return "category/edit";
        }
        categoryService.update(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Category category = categoryService.getDetail(id);
        if (category == null) {
            return "Lỗi rồi";
        }
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
