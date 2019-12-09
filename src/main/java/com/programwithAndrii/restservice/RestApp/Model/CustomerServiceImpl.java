package com.programwithAndrii.restservice.RestApp.Model;

import com.programwithAndrii.restservice.RestApp.Controllers.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomer(String id) {
        System.out.println("get " + id + " customer");
        return new Customer("", id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = new Customer(customer.getName(), customer.getId());
        System.out.println("post " + customer.getId() + " customer");
        return createdCustomer;
    }

    @Override
    public Customer updateCustomer(Customer customer){
        Customer updatesCustomer = new Customer(customer.getName(), customer.getId(), customer.getAddress());
        System.out.println("updated " + customer.getId() +" customer");
        return updatesCustomer;
    }

    @Override
    public void deleteCustomer(String id){
        System.out.println( "deleted id customer" + id);
    }
}
