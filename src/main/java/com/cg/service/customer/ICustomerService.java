package com.cg.service.customer;

import com.cg.model.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> findAll();

    Customer getOne(Long id);

    Customer findOne(Long id);

    Customer save(Customer customer);
}
