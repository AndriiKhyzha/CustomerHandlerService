package com.customer.handling.service.services;

import com.customer.handling.service.database.CustomerDB;
import com.customer.handling.service.database.repository.CustomerRepository;
import com.customer.handling.service.apimodel.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(Integer id) {
        Optional<CustomerDB> customerOptional = customerRepository.findById(id);
        return null;// todo do same logic as get address
    }

    @Override
    public com.customer.handling.service.apimodel.Customer createCustomer(com.customer.handling.service.apimodel.Customer customer) {
        com.customer.handling.service.apimodel.Customer createCustomer = new com.customer.handling.service.apimodel.Customer(customer.getName(), customer.getId());
        return createCustomer;
    }

    @Override
    public com.customer.handling.service.apimodel.Customer updateCustomer(com.customer.handling.service.apimodel.Customer customer){
        com.customer.handling.service.apimodel.Customer updateCustomer = new com.customer.handling.service.apimodel.Customer(customer.getName(), customer.getId(), customer.getAddress());
        System.out.println("updated");
        return updateCustomer;
    }

    @Override
    public void deleteCustomer(String id){
        System.out.println( "deleted id customer" + id);
    }
}
