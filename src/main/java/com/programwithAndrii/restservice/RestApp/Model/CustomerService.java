package com.programwithAndrii.restservice.RestApp.Model;

import com.programwithAndrii.restservice.RestApp.Controllers.Customer;

public interface CustomerService {

    Customer getCustomer(String id);

    void deleteCustomer(String id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);
}
