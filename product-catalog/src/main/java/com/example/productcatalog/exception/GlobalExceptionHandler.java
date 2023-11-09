package com.example.productcatalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(productNotFoundException.getMessage());
        exceptionResponse.setStatus(404);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
