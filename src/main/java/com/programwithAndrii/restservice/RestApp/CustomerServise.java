package com.programwithAndrii.restservice.RestApp;

import org.springframework.web.bind.annotation.PathVariable;

public class CustomerServise implements Servise {

    public String getCustomer(@PathVariable String id) {
        return id;
    }

    public void createCustomer(@PathVariable("id") String id, Customer customer) {
        Customer customer1 = new Customer(customer.getName(), customer.getId());
        System.out.println("post id customer" + id);
    }

    public void updateCustomer(@PathVariable("id") String id, Customer customer){
        Customer customer1 = new Customer(customer.getName(), customer.getId());
        System.out.println("created id customer" + id);
    }

    public void deleteCustomer(@PathVariable("id") String id){
        System.out.println( "deleted id customer" + id);
    }


}
