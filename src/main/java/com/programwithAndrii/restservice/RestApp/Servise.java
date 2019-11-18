package com.programwithAndrii.restservice.RestApp;

import org.springframework.web.bind.annotation.PathVariable;

public interface Servise {

    public String getCustomer(@PathVariable ("id") String id );

    public void deleteCustomer(@PathVariable("id") String id);

    public void createCustomer(@PathVariable("id") String id, Customer customer);

    public void updateCustomer(@PathVariable("id") String  id, Customer customer);
}
