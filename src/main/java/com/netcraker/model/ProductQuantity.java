/*
 * Copyright
 */

package com.netcraker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcraker.model.embeddable.ProductQuantityKey;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class describing the quantity of the product in the order.
 * Entity class, which models the join table
 * In this class we are using Many-to-Many Using a Composite Key "ProductQuantityKey".
 *
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
public class ProductQuantity {

    /**
     * Composite Key.
     * Used @EmbeddedId to mark the primary key, which is an instance
     * of the ProductQuantityKey class.
     */
    @EmbeddedId
    private ProductQuantityKey id = new ProductQuantityKey();

    /**
     * User order.
     */
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    /**
     * Product in order.
     */
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Quantity product.
     */
    private Long quantity;

}
