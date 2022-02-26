package com.allstate.catalogservice.exceptions;

public class ProductWithIDAlreadyPresentException extends Exception {

    public ProductWithIDAlreadyPresentException(String message){
        super(message);
    }

}
