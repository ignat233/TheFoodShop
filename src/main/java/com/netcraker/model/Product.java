/*
 * Copyright
 */

package com.netcraker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entity class describing the product.
 *
 * @since 0.0.1
 */
@Entity
@Data
@Table(name = "product")
public class Product {

    /**
     * Individual product number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Product name.
     */
    private String name;

    /**
     * Product price.
     */
    private double price;

    /**
     * Product count.
     */
    private int count;

    /**
     * Set up the inverse references to ProductQuantity.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProductQuantity> qty;

}
