package com.customer.handling.service.controller;

import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.services.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @ResponseBody
    @PostMapping(value = "/create", produces = "application/json")
    public  Address createAddress(@RequestBody Address address){
        return addressService.createAddress(address);
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = "application/json")
    public Address getAddress(@PathVariable("id") Integer id) {
        return addressService.getAddress(id);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void deleteAddress(@PathVariable("id") Integer id) {
        addressService.deleteAddress(id);
    }
}
