package com.project.UserRegistrationService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(userNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(userNotFoundException exception)
    {
        return new ResponseEntity<String>("user with email not found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserExists(UserAlreadyExistsException exception)
    {
        return new ResponseEntity<String>("user already exists",HttpStatus.ALREADY_REPORTED);
    }

}
