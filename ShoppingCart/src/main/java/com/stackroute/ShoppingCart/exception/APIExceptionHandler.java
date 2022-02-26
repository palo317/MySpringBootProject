package com.stackroute.ShoppingCart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(CatalogAndQuantityException.class)
    public ResponseEntity<String> handleProductExists(CatalogAndQuantityException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIdNotExistException.class)
    public ResponseEntity<String> handleProductExists(UserIdNotExistException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoProuctsFound.class)
    public ResponseEntity<String> handleNoProductsFoundInCart(NoProuctsFound exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
