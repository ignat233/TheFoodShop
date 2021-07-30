package com.netcraker.services;

import com.netcraker.model.Order;
import com.netcraker.model.Product;
import com.netcraker.model.ProductQuantity;
import com.netcraker.repository.OrderRepository;
import com.netcraker.repository.ProductRepository;
import com.netcraker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveOrder(Order order, HttpServletRequest request){
        Set<ProductQuantity> prdQty = order.getQty();
        for(ProductQuantity productQuantity : prdQty){
            productQuantity.setProduct(productRepository.findById(productQuantity.getProduct().getId()).get());
            productQuantity.setOrder(order);
        }

        order.setCreateDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    public List<Order> findAllOrder(){
        Iterable<Order> it = orderRepository.findAll();
        ArrayList<Order> orders = new ArrayList<>();
        it.forEach(e -> orders.add(e));

        return orders;
    }

    public List<Order> findAllUsersOrder(HttpServletRequest request){
        return orderRepository.findAllUsersOrders(request.getUserPrincipal().getName());
    }

    }
