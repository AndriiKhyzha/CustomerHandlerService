package com.programwithAndrii.restservice.RestApp.database.repo;

import com.programwithAndrii.restservice.RestApp.database.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
