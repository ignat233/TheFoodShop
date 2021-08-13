/*
 * Copyright
 */

package com.netcraker.repository;

import com.netcraker.model.Product;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository providing methods for Product Services.
 *
 * @since 0.0.1
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    /**
     * The method finds product with the id.
     *
     * @param id Id
     * @return Product
     */
    Optional<Product> findById(Long id);

    /**
     * The method finds user with the name.
     *
     * @param name Name
     * @return Product
     */
    Product findByName(String name);

}
