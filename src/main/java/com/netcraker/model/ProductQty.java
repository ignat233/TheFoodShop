package com.netcraker.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcraker.model.embeddable.ProductQtyKey;

import javax.persistence.*;

@Entity
public class ProductQty {

    @EmbeddedId
    private ProductQtyKey id = new ProductQtyKey();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private Long qty;

    public ProductQty(ProductQtyKey id, Order order, Product product, Long qty) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.qty = qty;
    }

    public ProductQty(  Order order, Product product, Long qty) {
        this.order = order;
        this.product = product;
        this.qty = qty;
    }

    public ProductQty() {

    }



    public ProductQtyKey getId() {
        return id;
    }

    public void setId(ProductQtyKey id) {
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

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ProductQty{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", qty=" + qty +
                '}';
    }
}
