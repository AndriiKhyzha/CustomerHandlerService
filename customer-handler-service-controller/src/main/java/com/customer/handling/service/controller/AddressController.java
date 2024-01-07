package com.customer.handling.service.controller;

import com.customer.handling.service.api.AddressData;
import com.customer.handling.service.mapping.ApiControllerModelMapper;
import com.customer.handling.service.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    private final ApiControllerModelMapper apiControllerModelMapper = Mappers.getMapper(ApiControllerModelMapper.class);

    @ResponseBody
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public ResponseEntity<AddressData> createAddress(@RequestBody AddressData addressData) {
        AddressData addressCreateData = apiControllerModelMapper.map(addressService.createAddress(apiControllerModelMapper.map(addressData)));
        log.info("create address with body: " + addressData);
        return ResponseEntity.ok(addressCreateData);
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressData> getAddress(@PathVariable("id") Integer id) {
        AddressData addressGetData = apiControllerModelMapper.map(addressService.getAddress(id));
        log.info("get request address body with DbId: " + addressGetData);
        return ResponseEntity.ok(addressGetData);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAddress(@PathVariable("id") Integer id) {
        addressService.deleteAddress(id);
        log.info("delete request address body with DbId: " + id);
    }

    @ResponseBody
    @PutMapping(value = "/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressData> updateAddress(@RequestBody AddressData addressData){
        AddressData addressDataUpdated = apiControllerModelMapper.map(addressService.updateAddress(apiControllerModelMapper.map(addressData)));
        log.info("update request address body with DbId: " + addressData);
        return ResponseEntity.ok(addressDataUpdated);
    }
}
