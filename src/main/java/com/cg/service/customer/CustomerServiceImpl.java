package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllByEmailLike(String email) {
        return customerRepository.findAllByEmailLike(email);
    }

    @Override
    public List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(String keySearch) {
        return customerRepository.findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(keySearch, keySearch, keySearch, keySearch);
    }

    @Override
    public Customer getOne(Long id) {
        return customerRepository.getOne(id);
    }

    @Override
    public Optional<Customer> findOne(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
