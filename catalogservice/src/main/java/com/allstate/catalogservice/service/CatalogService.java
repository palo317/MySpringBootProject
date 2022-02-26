package com.allstate.catalogservice.service;

import com.allstate.catalogservice.exceptions.ProductWithIDAlreadyPresentException;
import com.allstate.catalogservice.exceptions.ProductWithIDNotPresentException;
import com.allstate.catalogservice.model.Catalog;
import com.allstate.catalogservice.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    public Map<Object, Object> getAllProducts(){

        List<Catalog> products = catalogRepository.findAll();
        Map<Object,Object> map = new HashMap<>();
        map.put("Products", products);

        return map;
    }

    public  Catalog addNewProduct(Catalog catalog) throws ProductWithIDAlreadyPresentException {

        Optional<Catalog> productOptional =  catalogRepository.findById(catalog.getId());
        if(productOptional.isEmpty()) {
            catalogRepository.save(catalog);
            return catalog;
        }
        throw new ProductWithIDAlreadyPresentException("Product with ID is already present");
    }

    public  Catalog getProductByID(int id) throws ProductWithIDNotPresentException {

        Optional<Catalog> productOptional =  catalogRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductWithIDNotPresentException("Product with ID is NOT present");
        }
        return  productOptional.get();
    }

    public  Catalog updateProduct(Catalog catalog) throws ProductWithIDNotPresentException {

        Optional<Catalog> productOptional =  catalogRepository.findById(catalog.getId());
        if(productOptional.isEmpty()) {
            throw new ProductWithIDNotPresentException("Product with ID is NOT present");
        }
        catalogRepository.save(catalog);

        return catalog;
    }

    public  Catalog deleteProduct(int id) throws ProductWithIDNotPresentException {

        Optional<Catalog> productOptional =  catalogRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductWithIDNotPresentException("Product with ID is NOT present");
        }
        catalogRepository.deleteById(id);

        return productOptional.get();
    }

    public  boolean getIDExists(int id) throws ProductWithIDNotPresentException {

        Optional<Catalog> productOptional =  catalogRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductWithIDNotPresentException("Product with ID is NOT present");
        }
        return  true;
    }

    public  int getQtyByID(int id) throws ProductWithIDNotPresentException {

        Optional<Catalog> productOptional =  catalogRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductWithIDNotPresentException("Product with ID is NOT present");
        }
        return productOptional.get().getQuantity();
    }

    public  double getPriceByID(int id) throws ProductWithIDNotPresentException {

        Optional<Catalog> productOptional =  catalogRepository.findById(id);
        if(productOptional.isEmpty()) {
            return -1;
        }
        return productOptional.get().getPrice();
    }

    public  boolean updateQtyById(int id,int qty) throws ProductWithIDNotPresentException {

        Optional<Catalog> productOptional =  catalogRepository.findById(id);

        if(productOptional.isEmpty()) {
            return false;
        }
        else if(qty > productOptional.get().getQuantity()){
            return false;
        }
        else{
            Catalog catalog = new Catalog(id,productOptional.get().getName(),productOptional.get().getDescription(),
                              productOptional.get().getQuantity() - qty,productOptional.get().getPrice());

            catalogRepository.save(catalog);
        }

        return true;
    }

}
