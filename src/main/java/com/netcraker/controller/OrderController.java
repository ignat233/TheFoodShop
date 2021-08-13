/*
 * Copyright
 */

package com.netcraker.controller;

import com.netcraker.model.Order;
import com.netcraker.services.OrderService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for Order model.
 *
 * @since 0.0.1
 */
@Controller
public class OrderController {

    /**
     * OrderService field.
     */
    @Autowired
    private OrderService service;

    /**
     * Ordering.
     *
     * @param order Order
     * @param request Request
     */
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/addOrder")
    @ResponseBody
    public void addOrder(@RequestBody final Order order, final HttpServletRequest request) {
        this.service.saveOrder(order, request);
    }

    /**
     * Admin gets all orders.
     *
     * @return All orders.
     */
    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public List<Order> showAllOrder() {
        return this.service.findAll();
    }

    /**
     * Get page with all order.
     *
     * @return AdminOrder.html
     */
    @GetMapping("/adminOrder")
    public static String showToAdminOrder() {
        return "adminOrder";
    }

    /**
     * User gets all his orders.
     *
     * @param request Request.
     * @return All user's order
     */
    @GetMapping("/showUserOrder")
    @ResponseBody
    public List<Order> showOrdersOfUser(final HttpServletRequest request) {
        return this.service.findAllOrderOfUser(request);
    }

    /**
     * User gets page with all his order.
     *
     * @return UserOrder.html
     */
    @GetMapping("/userOrder")
    public static String userOrder() {
        return "userOrder";
    }

}
