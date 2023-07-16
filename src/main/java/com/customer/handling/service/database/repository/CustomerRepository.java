package com.customer.handling.service.database.repository;

import com.customer.handling.service.database.CustomerDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDB, Integer> {

}
