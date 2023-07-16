package com.customer.handling.service.services;

import com.customer.handling.service.database.AddressDB;
import com.customer.handling.service.database.repository.AddressRepository;
import com.customer.handling.service.apimodel.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address getAddress(Integer id) {
        Optional<AddressDB> addressOptional = addressRepository.findById(id);
        AddressDB addressDB = addressOptional
                .orElseThrow(() -> new RuntimeException("Address object with id:" + id + " not found"));
        // TODO: 16-Jul-23 read about Optional Java Util

        return new Address(addressDB.getCountry(), addressDB.getCity(), addressDB.getStreet(), addressDB.getNumber());
    }

    @Override
    public Address createAddress(Address address) {
        AddressDB addressDB =
                new AddressDB(address.getCountry(), address.getCity(), address.getStreet(), address.getNumber());
        AddressDB createdAddressDB = addressRepository.save(addressDB);
        return new Address(createdAddressDB.getCountry(), createdAddressDB.getCity(), createdAddressDB.getStreet(), createdAddressDB.getNumber());
    }

    @Override
    public void deleteAddress(Integer id){
        AddressDB addressDB = new AddressDB();

        addressRepository.delete(addressDB);
    }

    public AddressDB updateAddress(AddressDB addressDB, Integer id){
        return null;
    }
}
