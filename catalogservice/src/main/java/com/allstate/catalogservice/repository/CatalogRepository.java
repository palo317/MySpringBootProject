package com.allstate.catalogservice.repository;

import com.allstate.catalogservice.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Integer> {

    Optional<Catalog> findById(int id);
    Optional<Integer> findByQuantity(int qty);
    Optional<Catalog> deleteById(int id);

}
