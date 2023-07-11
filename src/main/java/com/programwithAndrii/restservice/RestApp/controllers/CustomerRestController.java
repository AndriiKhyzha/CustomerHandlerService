package com.programwithAndrii.restservice.RestApp.controllers;
import com.programwithAndrii.restservice.RestApp.models.Customer;
import com.programwithAndrii.restservice.RestApp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/{name}/{id}", produces = "application/json")
    public @ResponseBody
    Customer getCustomer(@PathVariable ("name") String name, @PathVariable("id") Integer id){
        return customerService.getCustomer(name,id);
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
