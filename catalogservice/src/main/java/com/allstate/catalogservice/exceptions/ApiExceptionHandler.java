package com.allstate.catalogservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ProductWithIDAlreadyPresentException.class)
    public ResponseEntity<String> handleProductExists(ProductWithIDAlreadyPresentException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductWithIDNotPresentException.class)
    public ResponseEntity<String> handleProductExists(ProductWithIDNotPresentException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
