package com.project.UserRegistrationService.Service;

import com.project.UserRegistrationService.Exceptions.UserAlreadyExistsException;
import com.project.UserRegistrationService.Exceptions.userNotFoundException;
import com.project.UserRegistrationService.Model.AdminRequest;
import com.project.UserRegistrationService.Model.User;
import com.project.UserRegistrationService.Model.UserCredentials;
import com.project.UserRegistrationService.Repository.AdminRequestRepository;
import com.project.UserRegistrationService.Repository.UserRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserRegistrationServiceImpl  implements UserRegistrationService{

    private Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private UserRegistrationRepository repository;
    @Autowired
    private JWTGeneratorService jwtGeneratorService;
    @Autowired
    private AdminRequestRepository adminRequestRepository;

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(repository.existsByEmailId(user.getEmailId()))
        {
            logger.error("user with email exists",user.getEmailId());
           throw new UserAlreadyExistsException("user with email exists") ;
        }
        repository.save(user);
        return user;
    }

    @Override
    public String authenticateUser(UserCredentials credentials) throws userNotFoundException {
        Optional<User> useroptional=repository.findByEmailId(credentials.getEmail());
        if(useroptional.isEmpty())
        {
            throw new userNotFoundException("user with email does not exists");
        }

        if(!credentials.getPassword().equals(useroptional.get().getPassword()))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return jwtGeneratorService.generateToken(credentials.getEmail(),useroptional.get().getRole());
    }

    @Override
    public String requestAdminAccess(AdminRequest adminRequest) {
        boolean checkEmail = adminRequestRepository.existsByEmailId(adminRequest.getEmailId());
        if(checkEmail==false) {
            String message = "admin request submitted for " + adminRequest.getEmailId();
            adminRequestRepository.save(adminRequest);
            return message;
        }
        return "Request has already been submitted for this email :"+adminRequest.getEmailId();

    }

    @Override
    public boolean isUserExists(String emailId) {
        Optional<User> userexists = repository.findByEmailId(emailId);
        if (userexists.isEmpty()) {
            return false;
        } else return true;
    }
}
