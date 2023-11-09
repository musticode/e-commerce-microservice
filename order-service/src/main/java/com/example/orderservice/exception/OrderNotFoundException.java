package com.example.orderservice.exception;

public class OrderNotFoundException extends RuntimeException{
    private String message;
    public OrderNotFoundException(String message){
        super(message);
    }
}
