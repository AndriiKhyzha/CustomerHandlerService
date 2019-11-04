package com.programwithAndrii.restservice.RestApp;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController("/customer")
public class CustomerRestController {

    @GetMapping("/{id}")
    public String getCustomer(@PathParam("id") String id){
        return id;
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathParam("id") String id) {
        System.out.println("removing id customer " + id);
    }

    // todo add post, put methods to rest controller
}
