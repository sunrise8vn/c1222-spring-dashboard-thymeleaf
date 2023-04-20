package com.cg.service.customer;

import com.cg.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<Customer> findAll();

    List<Customer> findAllByEmailLike(String email);

    List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(String keySearch);

    Customer getOne(Long id);

    Optional<Customer> findOne(Long id);

    Customer save(Customer customer);
}
