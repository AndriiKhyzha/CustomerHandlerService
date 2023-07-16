package com.customer.handling.service.database.repository;

import com.customer.handling.service.database.AddressDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressDB, Integer> {

}
