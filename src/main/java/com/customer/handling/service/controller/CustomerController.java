package com.customer.handling.service.controller;

import com.customer.handling.service.services.CustomerService;
import com.customer.handling.service.apimodel.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @GetMapping(value = "/{id}", produces = "application/json")
    public Customer getCustomer(@PathVariable("id") Integer id){
        return customerService.getCustomer(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody Customer createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        System.out.println(customer.getId());
        return customer;
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody Customer updateCustomer(@RequestBody Customer customer){
        Customer updatedCustomer = customerService.updateCustomer(customer);
        System.out.println(updatedCustomer);
        return updatedCustomer;
    }
}
