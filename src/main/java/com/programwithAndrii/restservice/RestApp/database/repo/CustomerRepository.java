package com.programwithAndrii.restservice.RestApp.database.repo;

import com.programwithAndrii.restservice.RestApp.database.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


}
