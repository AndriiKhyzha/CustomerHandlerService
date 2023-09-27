package com.customer.handling.service.service;

import com.customer.handling.service.api.Customer;

public interface CustomerService {

    Customer getCustomer(Integer id);

    void deleteCustomer(Integer id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);
}
