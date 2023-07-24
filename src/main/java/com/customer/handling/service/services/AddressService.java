package com.customer.handling.service.services;

import com.customer.handling.service.apimodel.Address;

public interface AddressService {

    Address getAddress(Integer id);

    Address createAddress(Address address);

    void deleteAddress(Integer id);

    Address updateAddress(Address address, Integer id);
}
