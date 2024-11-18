package com.customer.handling.service.controller;

import com.customer.handling.service.api.AddressApi;
import com.customer.handling.service.api.model.AddressData;
import com.customer.handling.service.exception.RequestNotValidException;
import com.customer.handling.service.mapping.ApiControllerModelMapper;
import com.customer.handling.service.service.AddressService;
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
public class AddressController implements AddressApi {

    @Autowired
    private AddressService addressService;

    private final ApiControllerModelMapper apiControllerModelMapper = Mappers.getMapper(ApiControllerModelMapper.class);

    @Override
    public ResponseEntity<AddressData> createAddress(@Parameter(name = "AddressData",description = "",required = true) @RequestBody @Valid AddressData addressData) {
        AddressData addressCreateData = apiControllerModelMapper
                .map(addressService.createAddress(apiControllerModelMapper.map(addressData)));
        log.info("create address with body: " + addressData);
        return ResponseEntity.ok(addressCreateData);
    }

    @Override
    public ResponseEntity<AddressData> getAddress(@Parameter(name = "addressId",description = "Status values that need to be considered for filter",required = true,in = ParameterIn.PATH) @PathVariable("addressId") Integer addressId) {
        if (addressId <= 0) {
            throw new RequestNotValidException("Id must not be negative or Zero");
        }
        AddressData addressGetData = apiControllerModelMapper.map(addressService.getAddress(addressId));
        log.info("get request address body with DbId: " + addressGetData);
        return ResponseEntity.ok(addressGetData);
    }

    @Override
    public ResponseEntity<Void> deleteAddress(@Parameter(name = "addressId",description = "Address Id for delete",required = true,in = ParameterIn.PATH) @PathVariable("addressId") Integer addressId) {
        if (addressId <= 0) {
            throw new RequestNotValidException("Id must not be negative or Zero");
        }
        addressService.deleteAddress(addressId);
        log.info("delete request address body with DbId: " + addressId);
        return null;
    }

    @Override
    public ResponseEntity<AddressData> updateAddress(@Parameter(name = "AddressData",description = "",required = true) @RequestBody @Valid AddressData addressData) {
        if (addressData.getDbId() <= 0) {
            throw new RequestNotValidException("Id must not be negative or Zero");
        }
        AddressData addressDataUpdated = apiControllerModelMapper
                .map(addressService.updateAddress(apiControllerModelMapper.map(addressData)));
        log.info("update request address body with DbId: " + addressData);
        return ResponseEntity.ok(addressDataUpdated);
    }
}