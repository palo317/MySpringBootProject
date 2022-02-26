package com.stackroute.ShoppingCart.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@FeignClient("catalog-service")
public interface CatalogServiceFeign {

    @GetMapping("/products/update/{id}/{qty}")
    Boolean checkCatalogIdAndQuantity(@PathVariable("id") int id, @PathVariable("qty") int qty);

    @GetMapping("/products/price/{id}")
    Double getPriceById(@PathVariable("id") int id);

}
