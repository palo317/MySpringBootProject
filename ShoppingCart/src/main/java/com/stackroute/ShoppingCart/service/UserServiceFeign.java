package com.stackroute.ShoppingCart.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserServiceFeign {

    @GetMapping("/users/findifuserexists/{emailId}")
    Boolean checkUserExist( @PathVariable("emailId") String emailId );
}
