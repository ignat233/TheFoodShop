/*
 * Copyright
 */

package com.netcraker.services;

import com.netcraker.model.Order;
import com.netcraker.model.ProductQuantity;
import com.netcraker.repository.OrderRepository;
import com.netcraker.repository.ProductRepository;
import com.netcraker.services.method.FindAll;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    //@checkstyle MemberNameCheck (2 lines)
    private ProductRepository productRepo;

    /**
     * Dependency injection through the constructor.
     *
     * @param repository Order Repository
     */
    public OrderService(final OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * The method saves the order to the database.
     *
     * @param order Order
     * @param request Request
     */
    public void saveOrder(final Order order, final HttpServletRequest request) {
        final Set<ProductQuantity> products = order.getProducts();
        //@checkstyle LocalFinalVariableNameCheck (2 lines)
        for (final ProductQuantity productQuantity : products) {
            productQuantity.setOrder(order);
            productQuantity.setProduct(this.productRepo
                .findByName(productQuantity.getProduct().getName())
            );
        }
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
