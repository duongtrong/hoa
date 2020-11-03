package com.hanu.spring.controller;

import com.hanu.spring.model.Product;
import com.hanu.spring.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        List<Product> productResponseList = productService.getAllProduct();
        model.addAttribute("productResponseList", productResponseList);
        return "product/data";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("productRequest") @Validated Product productRequest) {
        productService.createOrUpdate(productRequest);
        return "redirect:/products";
    }

    @GetMapping("/create")
    public String createO(@ModelAttribute("productRequest") Product product, Model model) {
        model.addAttribute("productRequest", product);
        return "product/create";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Product product = productService.getDetail(id);
        if (product == null) {
            return "Lỗi rồi";
        }
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/edit/{id}")
    public String editO(@ModelAttribute("product") @Validated Product product, @PathVariable("id") Long id, BindingResult result) {
        if (result.hasErrors()) {
            product.setId(id);
            return "product/edit";
        }
        productService.createOrUpdate(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Product product = productService.getDetail(id);
        if (product == null) {
            return "Lỗi rồi";
        }
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
