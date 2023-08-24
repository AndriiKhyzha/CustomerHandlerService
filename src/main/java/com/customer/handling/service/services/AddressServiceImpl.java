package com.customer.handling.service.services;

import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.database.AddressDB;
import com.customer.handling.service.database.repository.AddressRepository;
import com.customer.handling.service.mapping.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    public Address getAddress(Integer id)  {
        AddressDB addressDB = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address object with id:" + id + " not found"));

        return customerMapper.mapToAddress(addressDB);
    }

    @Override
    public Address createAddress(Address address) {
        AddressDB createdAddressDB = addressRepository.save(customerMapper.mapToAddressDB(address));

        return customerMapper.mapToAddress(createdAddressDB);
    }

    @Override
    public void deleteAddress(Integer id){
        addressRepository.deleteById(id);
    }

    @Override
    public Address updateAddress(Address address) {
        AddressDB findedaddressDB = addressRepository.findById(address.getDbId()).
                orElseThrow(() -> new RuntimeException("Address object with id:" + address.getDbId() + " not found"));

        AddressDB updatedAddressDB = addressRepository.save(customerMapper.mapToAddressDB(findedaddressDB, address));

        return customerMapper.mapToAddress(updatedAddressDB);
    }
}