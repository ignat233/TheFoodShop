package com.netcraker.controller;

import com.netcraker.model.Product;
import com.netcraker.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/cart")
    public String basket(){
        return "cart";
    }

    @GetMapping("/product")
    @ResponseBody
    public List<Product> showProduct(){
    return productService.findAll();
    }


}
