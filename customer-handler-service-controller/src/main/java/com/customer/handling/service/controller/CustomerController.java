package com.customer.handling.service.controller;

import com.customer.handling.service.api.CustomerApi;
import com.customer.handling.service.api.model.CustomerData;
import com.customer.handling.service.exception.RequestNotValidException;
import com.customer.handling.service.mapping.ApiControllerModelMapper;
import com.customer.handling.service.service.CustomerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class CustomerController implements CustomerApi {

    @Autowired
    private CustomerService customerService;

    private final ApiControllerModelMapper apiControllerModelMapper = Mappers.getMapper(ApiControllerModelMapper.class);

    @Override
    public ResponseEntity<CustomerData> getCustomer(@Parameter(name = "customerId", description = "Status values that need to be considered for filter", required = true, in = ParameterIn.PATH) @PathVariable("customerId") Integer customerId) {
        if (customerId <= 0) {
            throw new RequestNotValidException("Id must not be negative or Zero");
        }
        CustomerData customerData = apiControllerModelMapper.map(customerService.getCustomer(customerId));
        log.info("get request customer body: " + customerData);
        return ResponseEntity.ok(customerData);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(@Parameter(name = "customerId", description = "Customer Id for delete", required = true, in = ParameterIn.PATH) @PathVariable("customerId") Integer customerId) {
        if (customerId <= 0) {
            throw new RequestNotValidException("Id must not be negative or Zero");
        }
        customerService.deleteCustomer(customerId);
        log.info("delete request customer body with Id: " + customerId);
        return null;
    }

    @Override
    public ResponseEntity<CustomerData> createCustomer(@Parameter(name = "CustomerData",description = "",required = true) @RequestBody @Valid CustomerData customerData) {
        CustomerData customerCreateData =  apiControllerModelMapper.map(customerService.createCustomer(apiControllerModelMapper.map(customerData)));
        log.info("post request customer body with Id: " + customerData.getDbId());
        return ResponseEntity.ok(customerCreateData);
    }

    @Override
    public ResponseEntity<CustomerData> updateCustomer(@Parameter(name = "CustomerData",description = "",required = true) @RequestBody @Valid CustomerData customerData) {
        if (customerData.getDbId() <= 0) {
            throw new RequestNotValidException("Id must not be negative or Zero");
        }
        CustomerData customerDataUpdated = apiControllerModelMapper
                .map(customerService.updateCustomer(apiControllerModelMapper.map(customerData)));
        log.info("update request customer body with Id: " + customerData.getDbId());
        return ResponseEntity.ok(customerDataUpdated);
    }
}
