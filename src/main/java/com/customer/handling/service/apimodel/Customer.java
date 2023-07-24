package com.customer.handling.service.apimodel;

public class Customer {

    private String name;
    private Integer id;
    private Address address;

    public void setAddress(Address address){
        this.address = address;
    }

    public Address getAddress(){
        return address;
    }

    public Customer(){
    }

    public Customer(Integer id,String name){
        this.name = name;
        this.id = id;
    }

    public Customer(String name, Integer id, Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public Integer getId(){
        return id;
    }
}

