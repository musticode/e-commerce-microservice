package com.example.shoppingcart.exception;

public class ItemNotFoundException extends RuntimeException{
    private String message;

    public ItemNotFoundException(String message){
        super(message);
    }
}
