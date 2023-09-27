package com.customer.handling.service.controller;

import com.customer.handling.service.api.Address;
import com.customer.handling.service.service.AddressService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ResponseBody
    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public Address createAddress(@RequestBody Address address) {
        log.info("get request body: " + address);
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

    @ResponseBody
    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public Address updateAddress(@RequestBody Address address){
        return addressService.updateAddress(address);
    }
}
