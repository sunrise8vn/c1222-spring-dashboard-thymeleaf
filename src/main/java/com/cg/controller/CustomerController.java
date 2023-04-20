package com.cg.controller;

import com.cg.model.Customer;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public String listPage(Model model) {

        List<Customer> customers = customerService.findAll();

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
        Optional<Customer> customerOptional = customerService.findOne(customerId);
        model.addAttribute("customer", customerOptional.get());
        return "/cp/customer/edit";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keySearch, Model model) {
        List<Customer> customers;
        if (keySearch.equals("")) {
            customers = customerService.findAll();
            model.addAttribute("customers", customers);
        }
        else {
            keySearch = "%" + keySearch + "%";
            customers = customerService.findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(keySearch);
        }

        model.addAttribute("customers", customers);
        return "/cp/customer/list";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer, Model model) {
        customerService.save(customer);

        model.addAttribute("customer", new Customer());
        return "/cp/customer/create";
    }

    @PostMapping("/edit/{customerId}")
    public String editPage(Model model, @PathVariable Long customerId, @ModelAttribute Customer customer) {
        customer.setId(customerId);
        customerService.save(customer);
        model.addAttribute("customer", customer);
        return "/cp/customer/edit";
    }
}
