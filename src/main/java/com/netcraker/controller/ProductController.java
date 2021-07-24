package com.netcraker.controller;

import com.netcraker.model.Order;
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
    public String cart(Model model){
        model.addAttribute("data",new Order());
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
            String productMassage = "Такой продукт уже существует";
            model.addAttribute("productMassage", productMassage);
            return "adminProduct";
        }

        String productMassage = "Продукт добавлен";
        model.addAttribute("productMassage", productMassage);
        return "adminProduct";
    }

    @GetMapping("/adminProduct")
    public String showProductToAdmin(Model model){
        model.addAttribute("product",new Product());
        return "adminProduct";
    }

    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute Product product, Model model){
        if(!productService.editProduct(product)) {
            String productMassage = "Назавние продукта введено некоректно";
            model.addAttribute("editMassage", productMassage);
            return "adminProduct";
        }
        String productMassage = "Продукт отредактирован";
        model.addAttribute("editMassage", productMassage);
        return "adminProduct";
    }

}
