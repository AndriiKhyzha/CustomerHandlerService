package com.programwithAndrii.restservice.RestApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController("/customer")
public class CustomerRestController {

    @GetMapping("/{id}")
    public String getCustomer(@PathParam("id") String id){
        return id;
    }

}
