package com.customer.handling.service.controller;

import com.customer.handling.service.api.AddressData;
import com.customer.handling.service.mapping.ApiControllerModelMapper;
import com.customer.handling.service.service.AddressService;
import lombok.extern.log4j.Log4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    private final ApiControllerModelMapper apiControllerModelMapper = Mappers.getMapper(ApiControllerModelMapper.class);

    @ResponseBody
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public AddressData createAddress(@RequestBody AddressData addressData) {
        log.info("get request body: " + addressData);
        return apiControllerModelMapper.map(addressService.createAddress(apiControllerModelMapper.map(addressData)));
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressData getAddress(@PathVariable("id") Integer id) {
        return apiControllerModelMapper.map(addressService.getAddress(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAddress(@PathVariable("id") Integer id) {
        addressService.deleteAddress(id);
    }

    @ResponseBody
    @PutMapping(value = "/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressData updateAddress(@RequestBody AddressData addressData){
        return apiControllerModelMapper.map(addressService.updateAddress(apiControllerModelMapper.map(addressData)));
    }
}
