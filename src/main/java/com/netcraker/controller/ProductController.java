package com.netcraker.controller;

import com.netcraker.model.Product;
import com.netcraker.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public String index( Model model, Model model2){
        Iterable<Product> products = productService.findAll();
        model.addAttribute("product",products);
        return "index";
    }

    @GetMapping("/basket")
    public String hello(){
        return "basket";
    }

//    @GetMapping("/index1")
//    public String countBasket(Model model,Model model2){
//        count++;
//        Iterable<Product> products = productService.findAll();
//        model.addAttribute("product",products);
//        model2.addAttribute("count",count);
//        return "index";
//    }
}
