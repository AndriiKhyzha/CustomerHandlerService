package com.restservice.RestApp;

import com.restservice.RestApp.Controllers.Customer;
import com.restservice.RestApp.Model.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    private static final Logger logger = Logger.getLogger(CustomerRestController.class);

    @GetMapping(value = "/{id}")
    public @ResponseBody Customer getCustomer(@PathVariable("id") String Id, Customer customer){
        customerService.getCustomer(customer);
        Customer customer1 = new Customer(customer.getName(), customer.getId(), customer.getAddress());
        logger.info("Get " + customer.getId() + " customer");
        return customerService.getCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String id) {
        logger.info("Deleted " + id + " customer");
        customerService.deleteCustomer(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody Customer createCustomer(@RequestBody Customer customer) {
        logger.info("Post " + customer.getId() +" customer");
        Customer postedCustomer = customerService.createCustomer(customer);
        System.out.println(postedCustomer);
        return postedCustomer;
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody Customer updateCustomer(@RequestBody Customer customer){
        logger.info("Put " + customer.getId() + " customer");
        Customer updatedCustomer = customerService.updateCustomer(customer);
        System.out.println(updatedCustomer);
        return updatedCustomer;
    }
}
