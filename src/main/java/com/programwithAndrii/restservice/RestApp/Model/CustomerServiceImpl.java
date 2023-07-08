package com.programwithAndrii.restservice.RestApp.Model;

import com.programwithAndrii.restservice.RestApp.Controllers.Customer;
import com.programwithAndrii.restservice.RestApp.Model.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomer(String name, String id)
    {
        Customer customer = new Customer();
        return new Customer(customer.getName(), customer.getId());
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
