package com.allstate.catalogservice.controller;

import com.allstate.catalogservice.exceptions.ProductWithIDAlreadyPresentException;
import com.allstate.catalogservice.exceptions.ProductWithIDNotPresentException;
import com.allstate.catalogservice.model.Catalog;
import com.allstate.catalogservice.service.CatalogService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/api/info")
    public String appInfo(){
        return "Product Service is Up and Running";
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAllProducts(){

        Map<Object, Object> map = catalogService.getAllProducts();
        ResponseEntity<Map<Object, Object>> responseEntity = new ResponseEntity<Map<Object,Object>>(map, HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addNewProductHandler(@RequestBody Catalog catalog) throws ProductWithIDAlreadyPresentException{

        ResponseEntity<?> responseEntity;

        Catalog newProduct = catalogService.addNewProduct(catalog);
        responseEntity = new ResponseEntity<Catalog>(newProduct,HttpStatus.CREATED);

        return responseEntity;
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getProductsByID(@PathVariable("id") int id) throws ProductWithIDNotPresentException {

        ResponseEntity<?> responseEntity;

        Catalog result = catalogService.getProductByID(id);
        responseEntity = new ResponseEntity<Catalog>(result, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping(value = "/find/{id}")
    public boolean getIDExists(@PathVariable("id") int id) throws ProductWithIDNotPresentException {

        boolean result = catalogService.getIDExists(id);
        return result;
    }

    @GetMapping(value = "/qty/{id}")
    public int getQtyByID(@PathVariable("id") int id) throws ProductWithIDNotPresentException {

        int quantity = catalogService.getQtyByID(id);
        return quantity;
    }

    @GetMapping(value = "/price/{id}")
    public double getPriceByID(@PathVariable("id") int id) throws ProductWithIDNotPresentException {

        double price = catalogService.getPriceByID(id);
        return price;
    }

    @Hidden
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateProductHandler(@RequestBody Catalog catalog, @PathVariable("id") int id) throws ProductWithIDNotPresentException{

        ResponseEntity<?> responseEntity;

            catalog.setId(id);
            Catalog existingProduct = catalogService.updateProduct(catalog);
            responseEntity = new ResponseEntity<Catalog>(existingProduct,HttpStatus.OK);

        return responseEntity;
    }

    @Hidden
    @GetMapping("/update/{id}/{qty}")
    public boolean updateQtyById(@PathVariable("id") int id, @PathVariable("qty") int qty) throws ProductWithIDNotPresentException{

        boolean response = catalogService.updateQtyById(id,qty);
        return response;
    }

    @Hidden
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductHandler(@PathVariable("id") int id) throws ProductWithIDNotPresentException{

        ResponseEntity<?> responseEntity;

        Catalog deletedProduct = catalogService.deleteProduct(id);
        responseEntity = new ResponseEntity<Catalog>(deletedProduct,HttpStatus.OK);

        return responseEntity;
    }
}
