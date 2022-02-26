package com.project.UserRegistrationService.Exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String message)
    {
        super(message);
    }
}
