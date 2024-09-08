package com.customer.handling.service.exception;

public class RequestNotValidException extends RuntimeException{
    public RequestNotValidException(String message) {
        super(message);
    }
}
