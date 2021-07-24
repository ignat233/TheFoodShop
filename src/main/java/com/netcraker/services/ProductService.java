package com.netcraker.services;

import com.netcraker.model.Product;
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
        Iterable<Product> it = productRepository.findAll();
        ArrayList<Product> products = new ArrayList<>();
        it.forEach(e -> products.add(e));

        return products;
    }

    public boolean saveProduct(Product product) {
        Product productFromDB = productRepository.findByName(product.getName());
        if (productFromDB != null) {
            return false;
        }
        productRepository.save(product);
        return true;

    }

    public boolean editProduct(Product product){
        Product productFromDB = productRepository.findByName(product.getName());
        if(productFromDB == null) return false;
        productFromDB.setCount(product.getCount());
        productFromDB.setPrice(product.getPrice());
        productRepository.save(productFromDB);
        return true;
    }

}
