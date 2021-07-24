package com.netcraker.controller;


import com.netcraker.model.Order;
import com.netcraker.model.ProductQty;
import com.netcraker.repository.OrderRepository;
import com.netcraker.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order order, HttpServletRequest request) {
        orderService.saveOrder(order, request);
        return "redirect:/lk";
    }

    @GetMapping("/addOrder")
    public String addOrder() {
        return "redirect:/index";
    }


    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public List<Order> showAllOrder() {
        return orderService.findAllOrder();
    }


    @GetMapping("/adminOrder")
    public String showToAdminOrder() {
        return "adminOrder";
    }

    @GetMapping("/userOrder")
    public String userOrder(){
        return "userOrder";
    }

    @GetMapping("/showUserOrder")
    @ResponseBody
    public List<Order> showUsersOrder(HttpServletRequest request){
        return orderService.findAllUsersOrder(request);
    }
}
