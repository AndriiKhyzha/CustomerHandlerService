package com.restservice.RestApp.Model;

import com.restservice.RestApp.Controllers.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomer(Customer customer) {
        Customer getCustomerinfo = new Customer(customer.getName(), customer.getId(), customer.getAddress());
        System.out.println("get " + customer.getId() + " customer");
        return getCustomerinfo;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = new Customer(customer.getName(), customer.getId(), customer.getAddress());
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
        System.out.println( "deleted customer" + id);
    }
}
