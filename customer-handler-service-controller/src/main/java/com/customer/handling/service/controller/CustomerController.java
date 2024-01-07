package com.customer.handling.service.controller;

import com.customer.handling.service.api.CustomerData;
import com.customer.handling.service.mapping.ApiControllerModelMapper;
import com.customer.handling.service.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private final ApiControllerModelMapper apiControllerModelMapper = Mappers.getMapper(ApiControllerModelMapper.class);

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerData> getCustomer(@PathVariable("id") Integer id){
        CustomerData customerData = apiControllerModelMapper.map(customerService.getCustomer(id));
        log.info("get request customer body: " + customerData.dbId() + customerData);
        return ResponseEntity.ok(customerData);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        log.info("delete request customer body with Id: " + id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public @ResponseBody ResponseEntity<CustomerData> createCustomer(@RequestBody CustomerData customerData) {
        CustomerData customerCreateData =  apiControllerModelMapper.map(customerService.createCustomer(apiControllerModelMapper.map(customerData)));
        log.info("post request customer body with Id: " + customerData.dbId());
        return ResponseEntity.ok(customerCreateData);
    }

    @ResponseBody
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public ResponseEntity<CustomerData> updateCustomer(@RequestBody CustomerData customerData){
        CustomerData customerDataUpdated = apiControllerModelMapper
                .map(customerService.updateCustomer(apiControllerModelMapper.map(customerData)));
        log.info("update request customer body with Id: " + customerData.dbId());
        return ResponseEntity.ok(customerDataUpdated);
    }
}
