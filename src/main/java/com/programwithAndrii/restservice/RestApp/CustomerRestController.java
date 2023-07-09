package com.programwithAndrii.restservice.RestApp;

import com.programwithAndrii.restservice.RestApp.Controllers.Customer;
import com.programwithAndrii.restservice.RestApp.Model.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping(value = "/{name, id}", produces = "application/json")
    public @ResponseBody
    Customer getCustomer(@PathVariable ("Robbie") String name, @PathVariable("3016") String id){
        Customer customer = new Customer(name, id);
        return customerServiceImpl.getCustomer(name, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String id) {
        customerServiceImpl.deleteCustomer(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody Customer createCustomer(@RequestBody Customer customer) {
        customerServiceImpl.createCustomer(customer);
        System.out.println(customer.getId());
        return customer;
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody Customer updateCustomer(@RequestBody Customer customer){
        Customer updatedCustomer = customerServiceImpl.updateCustomer(customer);
        System.out.println(updatedCustomer);
        return updatedCustomer;
    }
}
