package com.customer.handling.service.service;

import com.customer.handling.service.exception.ItemInDbNotFoundException;
import com.customer.handling.service.models.Customer;
import com.customer.handling.service.database.CustomerDB;
import com.customer.handling.service.database.repository.CustomerRepository;
import com.customer.handling.service.mapping.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Override
    public Customer getCustomer(Integer id) {
        CustomerDB customerDB = customerRepository.findById(id)
                .orElseThrow(() -> new ItemInDbNotFoundException("Customer with id: " + id + " not found"));

        return customerMapper.mapToCustomer(customerDB);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerDB createdCustomerDB = customerRepository.save(customerMapper.mapToCustomerDB(customer));

        return customerMapper.mapToCustomer(createdCustomerDB);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        CustomerDB customerDB = customerRepository.findById(customer.getDbId())
                .orElseThrow(() -> new ItemInDbNotFoundException("CustomerDb with id: " + customer.getDbId() + " not found"));

        CustomerDB updatedCustomerDB = customerRepository
                .save(customerMapper.mapToCustomerDb(customerDB, customer));

        return customerMapper.mapToCustomer(updatedCustomerDB);
    }

    @Override
    public void deleteCustomer(Integer id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemInDbNotFoundException("Address object with id: " + id + " not found and could no");
        }
    }
}
