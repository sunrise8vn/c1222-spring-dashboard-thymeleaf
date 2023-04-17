package com.cg.service.customer;

import com.cg.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl {

    public static List<Customer> customers = new ArrayList<Customer>();
    public static Long customerId = 1L;

    static {
        customers.add(new Customer(customerId++, "NVA", "nva@co.cc", "2345", "28 Nguyễn Tri Phương"));
        customers.add(new Customer(customerId++, "NVB", "nvb@co.cc", "3456", "29 Nguyễn Tri Phương"));
        customers.add(new Customer(customerId++, "NVC", "nvc@co.cc", "4567", "38 Nguyễn Tri Phương"));
        customers.add(new Customer(customerId++, "NVD", "nvd@co.cc", "5678", "39 Nguyễn Tri Phương"));
    }

    public static List<Customer> init() {
        return customers;
    }

    public static Customer findById(Long id) {
        Customer customer = new Customer();

        for (Customer item : customers) {
            if (item.getId().equals(id)) {
                customer = item;
            }
        }

        return customer;
    }
}
