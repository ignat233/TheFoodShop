/*
 * Copyright
 */

package com.netcraker.model.embeddable;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

/**
 * Composite key class for ProductQuantity class.
 *
 * @since 0.0.1
 */
@Embeddable
@Data
public class ProductQuantityKey implements Serializable {

    /**
     * Order part of a composite key.
     *
     * @checkstyle MemberNameCheck (4 lines) Allow an uppercase letter in the field name.
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * Product part of a composite key.
     *
     * @checkstyle MemberNameCheck (4 lines) Allow an uppercase letter in the field name.
     */
    @Column(name = "product_id")
    private Long productId;

}
