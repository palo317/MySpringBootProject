package com.allstate.catalogservice.exceptions;

public class ProductWithIDNotPresentException extends Exception {

    public ProductWithIDNotPresentException(String message) {
        super(message);
    }
}
