package com.netcraker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double price;
    private int count;

//    @ManyToMany(mappedBy = "products")
//    private Set<Order> order;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<ProductQty> qty;

//    public Set<Order> getOrder() {
//        return order;
//    }
//
//    public void setOrder(Set<Order> order) {
//        this.order = order;
//    }

    public Set<ProductQty> getQty() {
        return qty;
    }

    public void setQty(Set<ProductQty> qty) {
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
