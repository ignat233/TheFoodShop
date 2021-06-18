package com.netcraker.services;

import com.netcraker.model.Product;
import com.netcraker.model.User;
import com.netcraker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {

//        Iterable it = productRepository.findAll();
//
//        List products = new ArrayList<User>();
//        it.forEach(e -> products.add(e));

        return productRepository.findAll();
    }

}
