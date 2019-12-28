package com.restservice.RestApp.Controllers;

public class Customer {

    private String name;
    private String id;
    private Address address;

    public void setAddress(Address address){
        this.address = address;
    }

    public Address getAddress(){
        return address;
    }

    public Customer(){

    }

    public Customer(String name, String id){
        this.name = name;
        this.id = id;
    }

    public Customer(String name, String id, Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }
}

