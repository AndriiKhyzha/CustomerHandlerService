package com.customer.handling.service.services;

import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.database.AddressDB;
import com.customer.handling.service.database.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public Address getAddress(Integer id)  {
        AddressDB addressDB = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address object with id:" + id + " not found"));

        return Address.builder()
                .country(addressDB.getCountry())
                .city(addressDB.getCity())
                .street(addressDB.getStreet())
                .number(addressDB.getNumber())
                .build();
    }

    @Override
    public Address createAddress(Address address) {
        AddressDB addressDB = AddressDB.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .street(address.getStreet())
                .number(address.getNumber())
                .build();

        AddressDB createdAddressDB = addressRepository.save(addressDB);

        return Address.builder()
                .dbId(createdAddressDB.getId())
                .country(createdAddressDB.getCountry())
                .city(createdAddressDB.getCity())
                .street(createdAddressDB.getStreet())
                .number(createdAddressDB.getNumber())
                .build();
    }

    @Override
    public void deleteAddress(Integer id){
        addressRepository.deleteById(id);
    }

    @Override
    public Address updateAddress(Address address) {
        AddressDB findedaddressDB = addressRepository.findById(address.getDbId()).
                orElseThrow(() -> new RuntimeException("Address object with id:" + address.getDbId() + " not found"));

        findedaddressDB.setCountry(address.getCountry());
        findedaddressDB.setCity(address.getCity());
        findedaddressDB.setStreet(address.getStreet());
        findedaddressDB.setNumber(address.getNumber());

        AddressDB updatedAddressDB = addressRepository.save(findedaddressDB);

        return Address.builder()
                .dbId(updatedAddressDB.getId())
                .country(updatedAddressDB.getCountry())
                .city(updatedAddressDB.getCity())
                .street(updatedAddressDB.getStreet())
                .number(updatedAddressDB.getNumber())
                .build();
    }
}