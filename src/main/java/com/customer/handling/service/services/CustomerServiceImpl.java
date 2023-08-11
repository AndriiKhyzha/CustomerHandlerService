package com.customer.handling.service.services;

import com.customer.handling.service.apimodel.Customer;
import com.customer.handling.service.database.CustomerDB;
import com.customer.handling.service.database.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(Integer id) {
        CustomerDB customerDB = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer with id: "+ id + "not found")  );
        return Customer.builder()
                .dbId(customerDB.getId())
                .name(customerDB.getName())
                .build();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerDB createCustomerDB = new CustomerDB(customer.getDbId(),customer.getName());

        CustomerDB createdCustomerDB = customerRepository.save(createCustomerDB);

        return Customer.builder()
                .dbId(createdCustomerDB.getId())
                .name(createdCustomerDB.getName())
                .build();
    }

    @Override
    public Customer updateCustomer(Customer customer){
        CustomerDB customerDB = customerRepository.findById(customer.getDbId())
                .orElseThrow(() -> new RuntimeException("CustomerDb with id: " + customer.getDbId() + "not found"));
        customerDB.setName(customer.getName());

        CustomerDB updatedCustomerDB = customerRepository.save(customerDB);

        return Customer.builder()
                .dbId(updatedCustomerDB.getId())
                .name(updatedCustomerDB.getName())
                .build();
    }

    @Override
    public void deleteCustomer(Integer id){
        customerRepository.deleteById(id);
    }
}
