package com.customer.handling.service.exception;

public class ItemInDbNotFoundException extends RuntimeException {

    public ItemInDbNotFoundException(String message) {
        super(message);
    }
}
