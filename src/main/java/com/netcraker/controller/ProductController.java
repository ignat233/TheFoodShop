package com.netcraker.controller;

import com.netcraker.model.Product;
import com.netcraker.model.User;
import com.netcraker.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public String cart(){
        return "cart";
    }

    @GetMapping("/product")
    @ResponseBody
    public List<Product> showProduct(){
    return productService.findAll();
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product, Model model) {
        if (!productService.saveProduct(product)) {
            model.addAttribute("productMassage", "Такой продукт уже существует");
            return "lkAdmin";
        }
        model.addAttribute("productMassage", "Продукт добавлен");
        return "lkAdmin";
    }

    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute Product product, Model model){
        productService.editProduct(product);
        model.addAttribute("editMassage", "Продукт отредактирован");
        return "lkAdmin";
    }

}
