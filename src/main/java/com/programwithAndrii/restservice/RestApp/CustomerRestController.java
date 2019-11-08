package com.programwithAndrii.restservice.RestApp;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}")
    public void postCustomer(@PathParam("id") String id) {
        System.out.println("post id customer " + id);
    }

    @PutMapping("/{id}")
    public void putCustomer(@PathParam("id") String id){
        System.out.println("put id customer " + id);
    }
    // todo add post, put methods to rest controller
}
