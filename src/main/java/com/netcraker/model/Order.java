package com.netcraker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonProperty("quantity")
    Set<ProductQuantity> quantity;


    @Column(name = "create_date")
    private LocalDateTime createDate;


    public Order() {
    }

    public Order(User user, Set<ProductQuantity> qty, LocalDateTime createDate) {
        this.user = user;
        this.quantity= qty;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ProductQuantity> getQty() {
        return quantity;
    }

    public void setQty(Set<ProductQuantity> quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }


}
