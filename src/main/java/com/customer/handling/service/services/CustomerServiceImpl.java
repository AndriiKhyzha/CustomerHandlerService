package com.customer.handling.service.services;

import com.customer.handling.service.database.AddressDB;
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
        CustomerDB customerDB = customerOptional.orElseThrow(() -> new RuntimeException("Customer with id: "+ id + "not found")  );
        return new Customer(customerDB.getId(),customerDB.getName());
        // todo do same logic as get address -> done
    }

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerDB createCustomerDB = new CustomerDB(customer.getId(),customer.getName());
        CustomerDB createdCustomerDB = customerRepository.save(createCustomerDB);
        return new Customer(createdCustomerDB.getId(),createdCustomerDB.getName());
    }

    @Override
    public Customer updateCustomer(Customer customer, Integer id){
        Optional <CustomerDB> getCustomerDB = customerRepository.findById(id);
        CustomerDB customerDB = getCustomerDB.orElseThrow(() -> new RuntimeException("CustomerDb with id: " + id + "not found"));
        CustomerDB updateCustomerDB = new CustomerDB(customer.getId(), customer.getName());
        CustomerDB updatedCustomerDB = customerRepository.save(updateCustomerDB);
        return new Customer(updatedCustomerDB.getId(), updatedCustomerDB.getName());
    }

    @Override
    public void deleteCustomer(Integer id){
        System.out.println( "deleted id customer" + id);
    }
}
