package com.hanu.spring.service;

import com.hanu.spring.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomer();

    void create(Customer customer);

    void update(Customer customer);

    Customer getDetail(Long id);

    void deleteCustomer(Long id);
}
