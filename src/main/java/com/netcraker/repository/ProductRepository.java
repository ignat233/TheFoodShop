package com.netcraker.repository;

import com.netcraker.model.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    @Query(value = "SELECT * from product  where name=?1", nativeQuery = true)
    Product findByName(String name);

    @Query(value = "SELECT * from product  where id=?1", nativeQuery = true)
    Product findProductById(Long id);

}
