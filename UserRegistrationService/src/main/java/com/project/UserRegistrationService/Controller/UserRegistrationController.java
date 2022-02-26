package com.project.UserRegistrationService.Controller;


import com.project.UserRegistrationService.Exceptions.UserAlreadyExistsException;
import com.project.UserRegistrationService.Exceptions.userNotFoundException;
import com.project.UserRegistrationService.Model.AdminRequest;
import com.project.UserRegistrationService.Model.User;
import com.project.UserRegistrationService.Model.UserCredentials;
import com.project.UserRegistrationService.Service.CatalogueServiceClient;
import com.project.UserRegistrationService.Service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

public class UserRegistrationController
{
    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private CatalogueServiceClient catalogueServiceClient;

    @PostMapping("/users/register")
    public User registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        return userRegistrationService.registerUser(user);
    }

    @PostMapping("/users/authenticate")
    public Map<String,String> authenticate(@RequestBody UserCredentials userCredentials) throws userNotFoundException {
         String token=userRegistrationService.authenticateUser(userCredentials);
         return Map.of("token",token);
     }

    @PostMapping("/users/requestAdminAccess")
    public String requestAdminAccess(@RequestBody AdminRequest adminRequest)  {
        String message=userRegistrationService.requestAdminAccess(adminRequest);
        return message;
    }

    @GetMapping(value = "/users/getAll")
    public ResponseEntity<?> getAllProducts(){

        Map<Object, Object> map = catalogueServiceClient.getAllProducts();
        ResponseEntity<Map<Object, Object>> responseEntity = new ResponseEntity<Map<Object,Object>>(map, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping(value = "/users/findifuserexists/{emailId}")
    public boolean findIfUserExists(@PathVariable("emailId") String emailId){
        boolean result=userRegistrationService.isUserExists(emailId);
        return result;
    }

}
