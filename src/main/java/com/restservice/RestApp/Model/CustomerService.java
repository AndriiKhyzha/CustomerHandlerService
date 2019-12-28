package com.restservice.RestApp.Model;

import com.restservice.RestApp.Controllers.Customer;

public interface CustomerService {

    Customer getCustomer(Customer customer);

    void deleteCustomer(String id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);
}
