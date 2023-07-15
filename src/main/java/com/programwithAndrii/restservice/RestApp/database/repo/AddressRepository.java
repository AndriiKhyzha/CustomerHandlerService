package com.programwithAndrii.restservice.RestApp.database.repo;

import com.programwithAndrii.restservice.RestApp.database.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address getAddress(Address address, Integer id);

    void deleteAddress(Integer id);

    Address createAddress(Address address);

    Address updateAddress(Address address, Integer id);

}
