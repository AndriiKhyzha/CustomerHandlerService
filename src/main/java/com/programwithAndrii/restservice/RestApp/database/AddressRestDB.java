package com.programwithAndrii.restservice.RestApp.database;

import com.programwithAndrii.restservice.RestApp.database.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AddressRestDB implements AddressRepository{

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address getAddress (Address address, Integer id){
       addressRepository.findById(id).get();
        return addressRepository.getAddress(address, id);
    }

    @Override
    public Address createAddress(Address address){
        addressRepository.save(address);
        return createAddress(address);
    }

    @Override
    public void deleteAddress(Integer id){
        Address address = new Address();

        addressRepository.delete(address);
    }

    @Override
    public Address updateAddress(Address address, Integer id){
        return null;
    }




}
