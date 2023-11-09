package com.example.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({CartNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleCartNotFoundException(CartNotFoundException exception){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(exception.getMessage());
        response.setStatus(404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
