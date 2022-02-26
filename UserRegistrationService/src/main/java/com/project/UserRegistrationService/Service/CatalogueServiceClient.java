package com.project.UserRegistrationService.Service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@FeignClient("catalog-service")
public interface CatalogueServiceClient {

    @GetMapping(value="/products/getAll")
    public Map<Object,Object> getAllProducts();




}
