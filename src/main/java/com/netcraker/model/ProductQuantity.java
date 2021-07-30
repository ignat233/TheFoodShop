package com.netcraker.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcraker.model.embeddable.ProductQuantityKey;

import javax.persistence.*;

@Entity
public class ProductQuantity {

    @EmbeddedId
    private ProductQuantityKey id = new ProductQuantityKey();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private Long quantity;

    public ProductQuantity(ProductQuantityKey id, Order order, Product product, Long quantity) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public ProductQuantity(Order order, Product product, Long quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public ProductQuantity() {

    }



    public ProductQuantityKey getId() {
        return id;
    }

    public void setId(ProductQuantityKey id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductQty{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
