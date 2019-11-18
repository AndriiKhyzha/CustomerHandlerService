package com.programwithAndrii.restservice.RestApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    CustomerServise cs = new CustomerServise();

    @GetMapping("/{id}")
    public String getCustomer(@PathVariable("id") String id){
        return id;
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String id) {
        System.out.println("removing id customer " + id);
    }

    @PostMapping("/{id}")
    public Customer createCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
        Customer customer1 = new Customer(customer.getName(), customer.getId());
        System.out.println(customer.getId());
        System.out.println("post id customer " + id);
        return customer1;
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer){
        Customer customer1 = new Customer(customer.getName(), customer.getId(), customer.getAddress());
        System.out.println(customer1);
        return customer1;
    }
}
