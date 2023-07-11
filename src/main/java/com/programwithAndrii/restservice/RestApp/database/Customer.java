package com.programwithAndrii.restservice.RestApp.database;
import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Customer(String name, Integer id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public Integer getId(){
        return id;
    }
}
