package com.programwithAndrii.restservice.RestApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    TextWriter text;
     public HelloWorld(){
         text = new FancyWriter();
     }
    @RequestMapping("/")
    public String index(){
        return text.WriteText("hi Andrew! ");
    }
}
