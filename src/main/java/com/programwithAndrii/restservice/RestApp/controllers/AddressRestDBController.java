package com.programwithAndrii.restservice.RestApp.controllers;

import com.programwithAndrii.restservice.RestApp.database.Address;
import com.programwithAndrii.restservice.RestApp.database.AddressRestDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressRestDBController {

    @Autowired
    AddressRestDB addressRestDB;

//    @PostMapping(value = "/{id}/{country}/{city}/{street}/{number}", produces = "application/json")
    @Modifying
//    @Query()
    @PostMapping(value = "/{id},{country},{city},{street},{number}", produces = "application/json")
    public @ResponseBody Address postAddress(@PathVariable("id") Integer id, @PathVariable("country") String country, @PathVariable("city") String city, @PathVariable("street") String street, @PathVariable("number") String number){
 //       Address address = new Address(id, country, city, street, number);
        return addressRestDB.createAddress(new Address(id, country, city, street, number));
    }





}
