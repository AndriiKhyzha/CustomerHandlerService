package com.programwithAndrii.restservice.RestApp.services;
import com.programwithAndrii.restservice.RestApp.database.repo.CustomerRepository;
import com.programwithAndrii.restservice.RestApp.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(String name, Integer id) {
        Optional<com.programwithAndrii.restservice.RestApp.database.Customer> customerOptional
                = customerRepository.findById(id);
        return null;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer createCustomer = new Customer(customer.getName(), customer.getId());
        System.out.println("created");
        return createCustomer;
    }

    @Override
    public Customer updateCustomer(Customer customer){
        Customer updateCustomer = new Customer(customer.getName(), customer.getId(), customer.getAddress());
        System.out.println("updated");
        return updateCustomer;
    }

    @Override
    public void deleteCustomer(String id){
        System.out.println( "deleted id customer" + id);
    }
}
