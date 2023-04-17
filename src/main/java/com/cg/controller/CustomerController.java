package com.cg.controller;


import com.cg.model.Customer;
import com.cg.service.customer.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping
    public String listPage(Model model) {

        List<Customer> customers = CustomerServiceImpl.init();

        model.addAttribute("customers", customers);

        return "/cp/customer/list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "/cp/customer/create";
    }

    @GetMapping("/edit/{customerId}")
    public String editPage(Model model, @PathVariable Long customerId) {
        Customer customer = CustomerServiceImpl.findById(customerId);
        model.addAttribute("customer", customer);
        return "/cp/customer/edit";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer, Model model) {

        customer.setId(CustomerServiceImpl.customerId++);
        CustomerServiceImpl.customers.add(customer);

        model.addAttribute("customer", new Customer());
        return "/cp/customer/create";
    }
}
