package com.programwithAndrii.restservice.RestApp.services;

import com.programwithAndrii.restservice.RestApp.models.Customer;

public interface CustomerService {

    Customer getCustomer(String name, Integer id);

    void deleteCustomer(String id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);
}
