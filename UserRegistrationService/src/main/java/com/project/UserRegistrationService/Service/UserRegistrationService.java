package com.project.UserRegistrationService.Service;

import com.project.UserRegistrationService.Exceptions.UserAlreadyExistsException;
import com.project.UserRegistrationService.Exceptions.userNotFoundException;
import com.project.UserRegistrationService.Model.AdminRequest;
import com.project.UserRegistrationService.Model.User;
import com.project.UserRegistrationService.Model.UserCredentials;

public interface UserRegistrationService {

   User  registerUser(User user) throws UserAlreadyExistsException;
   String authenticateUser(UserCredentials credentials ) throws userNotFoundException;
   String requestAdminAccess(AdminRequest adminRequest);
   boolean isUserExists(String emailId);
}

