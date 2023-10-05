package com.customer.handling.service.controller;

import com.customer.handling.service.api.CustomerData;
import com.customer.handling.service.mapping.ApiControllerModelMapper;
import com.customer.handling.service.service.CustomerService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private final ApiControllerModelMapper apiControllerModelMapper = Mappers.getMapper(ApiControllerModelMapper.class);

    @ResponseBody
    @GetMapping(value = "/{id}", produces = "application/json")
    public CustomerData getCustomer(@PathVariable("id") Integer id){
        return apiControllerModelMapper.map(customerService.getCustomer(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public @ResponseBody CustomerData createCustomer(@RequestBody CustomerData customerData) {
        return apiControllerModelMapper.map(customerService.createCustomer(apiControllerModelMapper.map(customerData)));
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public @ResponseBody CustomerData updateCustomer(@RequestBody CustomerData customerData){
        return apiControllerModelMapper.map(customerService.updateCustomer(apiControllerModelMapper.map(customerData)));
    }
}
