/*
 * Copyright
 */

package com.netcraker.services;

import com.netcraker.model.Order;
import com.netcraker.model.Product;
import com.netcraker.model.ProductQuantity;
import com.netcraker.repository.ProductRepository;
import com.netcraker.services.method.FindAll;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * Product Service.
 * Using for providing functionality to the product's capabilities.
 * The class extends the FindAll class.
 *
 * @since 0.0.1
 */
@Service
public class ProductService extends FindAll<Product, ProductRepository> {

    /**
     * ProductRepository field.
     */
    private final ProductRepository repository;

    /**
     * Dependency injection through the constructor.
     *
     * @param repository Product Repository
     */
    public ProductService(final ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * The method saves the product to the database.
     *
     * @param product Product
     * @return Boolean save
     */
    public boolean saveProduct(final Product product) {
        final boolean save;
        if (this.repository.findByName(product.getName()) == null) {
            this.repository.save(product);
            save = true;
        } else {
            this.repository.save(product);
            save = false;
        }
        return save;
    }

    /**
     * The method changes the product.
     *
     * @param product Product
     * @return Boolean edit
     */
    public boolean editProduct(final Product product) {
        final Product prd = this.repository.findByName(product.getName());
        final boolean edit;
        if (prd == null) {
            edit = false;
        } else {
            prd.setCount(product.getCount());
            prd.setPrice(product.getPrice());
            this.repository.save(prd);
            edit = true;
        }
        return edit;
    }

    /**
     * Filling an order with products.
     *
     * @param order Order
     */
    public void fillProductForOrder(final Order order) {
        final Set<ProductQuantity> products = order.getProducts();
        //@checkstyle LocalFinalVariableNameCheck (2 lines)
        for (
            final ProductQuantity productQuantity : products) {
            productQuantity.setOrder(order);
            productQuantity.setProduct(this.repository
                .findByName(productQuantity.getProduct().getName())
            );
        }
    }

}
