package com.cg.controller;


import com.cg.model.Customer;
import com.cg.service.customer.CustomerServiceImpl;
import com.cg.service.customer.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private ICustomerService customerService() {
        return new CustomerServiceImpl();
    }

    @GetMapping
    public String listPage(Model model) {

        List<Customer> customers = customerService().findAll();

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
        Customer customer = customerService().getOne(customerId);
        model.addAttribute("customer", customer);
        return "/cp/customer/edit";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer, Model model) {

//        customer.setId(CustomerServiceImpl.customerId++);
//        CustomerServiceImpl.customers.add(customer);

        customerService().save(customer);

        model.addAttribute("customer", new Customer());
        return "/cp/customer/create";
    }

    @PostMapping("/edit/{customerId}")
    public String editPage(Model model, @PathVariable Long customerId, @ModelAttribute Customer customer) {
        customer.setId(customerId);
        customerService().save(customer);
        model.addAttribute("customer", customer);
        return "/cp/customer/edit";
    }
}
