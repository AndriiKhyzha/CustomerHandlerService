package com.customer.handling.service.services;

import com.customer.handling.service.apimodel.Customer;

public interface CustomerService {

    Customer getCustomer(Integer id);

    void deleteCustomer(Integer id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer, Integer id);
}
