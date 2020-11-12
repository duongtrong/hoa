package com.hanu.spring.controller;

import com.hanu.spring.model.Customer;
import com.hanu.spring.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String list(Model model) {
        List<Customer> customerResponseList = customerService.getAllCustomer();
        model.addAttribute("customerResponseList", customerResponseList);
        return "customer/data";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("customerRequest") @Validated Customer customerRequest) {
        customerService.create(customerRequest);
        return "redirect:/customers";
    }

    @GetMapping("/create")
    public String createO(@ModelAttribute("customerRequest") Customer customer, Model model) {
        model.addAttribute("customerRequest", customer);
        return "customer/create";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.getDetail(id);
        if (customer == null) {
            return "Lỗi rồi";
        }
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @PostMapping("/edit/{id}")
    public String editO(@ModelAttribute("product") @Validated Customer customer, @PathVariable("id") Long id, BindingResult result) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "customer/edit";
        }
        customerService.update(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Customer customer = customerService.getDetail(id);
        if (customer == null) {
            return "Lỗi rồi";
        }
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
