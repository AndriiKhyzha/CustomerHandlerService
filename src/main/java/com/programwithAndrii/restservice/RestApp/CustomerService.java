package com.programwithAndrii.restservice.RestApp;

public interface CustomerService {

    Customer getCustomer(String id);

    void deleteCustomer(String id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);
}
