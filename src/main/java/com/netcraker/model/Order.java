/*
 * Copyright
 */

package com.netcraker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class describing the order of the user.
 *
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    /**
     * Individual order number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The user who made the order.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Set up the inverse references to ProductQuantity.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonProperty("products")
    private Set<ProductQuantity> products;

    /**
     * Time of ordering.
     *
     * @checkstyle MemberNameCheck (4 lines) Allow an uppercase letter in the field name.
     */
    @Column(name = "create_date")
    private LocalDateTime createDate;

}
