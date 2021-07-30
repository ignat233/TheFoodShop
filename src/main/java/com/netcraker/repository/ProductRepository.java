package com.netcraker.repository;

import com.netcraker.model.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    Optional<Product> findById(Long id);

    Product findByName(String name);

    @Query(value = "SELECT * FROM product WHERE id IN :id",nativeQuery = true)
    List<Product> findListProductsOfOrder(@Param("id") ArrayList<Long> id);

}
