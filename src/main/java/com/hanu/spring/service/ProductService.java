package com.hanu.spring.service;

import com.hanu.spring.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    void create(Product product);

    void update(Product product);

    Product getDetail(Long id);

    void deleteProduct(Long id);
}
