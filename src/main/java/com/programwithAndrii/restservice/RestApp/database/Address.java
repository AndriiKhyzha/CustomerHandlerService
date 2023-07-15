package com.programwithAndrii.restservice.RestApp.database;
import javax.persistence.*;
@Table
@Entity(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String country;

    private String city;

    private String street;

    private String number;

    private Address address;

    public Address(){

    }

    public Address (Integer id,String country, String city, String street, String number){
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Address getAddress(){
        return address;
    }

    public Integer getId(){
        return id;
    }
    public String getCountry(){
        return country;
    }

    public String getCity(){
        return city;
    }

    public String getStreet(){
        return street;
    }

    public String getNumber(){
        return number;
    }
}
