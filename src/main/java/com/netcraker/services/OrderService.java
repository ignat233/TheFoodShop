/*
 * Copyright
 */

package com.netcraker.services;

import com.netcraker.model.Order;
import com.netcraker.repository.OrderRepository;
import com.netcraker.services.method.FindAll;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * Order Service.
 * Using for providing functionality to the order's capabilities.
 * The class extends the FindAll class.
 *
 * @since 0.0.1
 */
@Service
public class OrderService extends FindAll<Order, OrderRepository> {

    /**
     * OrderRepository field.
     */
    private final OrderRepository repository;

    /**
     * ProductRepository field.
     */
    private final ProductService product;

    /**
     * Dependency injection through the constructor.
     *
     * @param repository Order Repository
     * @param product Product Service
     */
    public OrderService(final OrderRepository repository, final ProductService product) {
        super(repository);
        this.repository = repository;
        this.product = product;
    }

    /**
     * The method saves the order to the database.
     *
     * @param order Order
     */
    public void saveOrder(final Order order) {
        this.product.fillProductForOrder(order);
        order.setCreateDate(LocalDateTime.now());
        this.repository.save(order);
    }

    /**
     * The method finds all orders of the user.
     *
     * @param request Request
     * @return Orders
     */
    public List<Order> findAllOrderOfUser(final HttpServletRequest request) {
        return this.repository.findAllOrdersOfUser(request.getUserPrincipal().getName());
    }

}
