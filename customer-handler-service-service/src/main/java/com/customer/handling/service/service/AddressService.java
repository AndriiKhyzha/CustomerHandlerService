package com.customer.handling.service.service;

import com.customer.handling.service.models.Address;

public interface AddressService {

    Address getAddress(Integer id);

    Address createAddress(Address address);

    void deleteAddress(Integer id);

    Address updateAddress(Address address);
}
