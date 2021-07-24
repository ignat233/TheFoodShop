package com.netcraker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonProperty("quantity")
    Set<ProductQty> quantity;

//    @ManyToMany
//    @JoinTable(
//            name = "order_product",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Set<Product> products;

    @Column(name = "create_date")
    private LocalDateTime createDate;


    public Order() {
    }

    public Order(User user, Set<ProductQty> qty, LocalDateTime createDate) {
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

    public Set<ProductQty> getQty() {
        return quantity;
    }

    public void setQty(Set<ProductQty> quantity) {
        this.quantity = quantity;
    }

//    public Set<Product> getProduct() {
//        return products;
//    }
//
//    public void setProduct(Set<Product> product) {
//        this.products = product;
//    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", qty=" + quantity +
//                ", products=" + products +
                ", createDate=" + createDate +
                '}';
    }
}
