package com.programwithAndrii.restservice.RestApp;

public class FancyWriter implements TextWriter {

    CustomerRestController crc = new CustomerRestController();
    @Override
    public String WriteText(String s) {
        return crc.getCustomer("Vasya");
    }
}
