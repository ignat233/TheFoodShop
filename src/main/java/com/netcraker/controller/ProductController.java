/*
 * Copyright
 */

package com.netcraker.controller;

import com.netcraker.controller.massage.Massage;
import com.netcraker.model.Order;
import com.netcraker.model.Product;
import com.netcraker.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for Product model.
 *
 * @since 0.0.1
 * Methods are allowed to have more than one return.
 * Allow duplicate literals.
 */
@Controller
@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.OnlyOneReturn"})
public class ProductController {

    /**
     * ProductService field.
     */
    @Autowired
    private ProductService service;

    /**
     * Get main page with menu.
     *
     * @return Index.html (main page)
     */
    @GetMapping("/index")
    public static String index() {
        return "index";
    }

    /**
     * Get cart page.
     *
     * @param model Model
     * @return Cart.html
     */
    @GetMapping("/cart")
    public static String cart(final Model model) {
        model.addAttribute("data", new Order());
        return "cart";
    }

    /**
     * Get all product.
     *
     * @return All product
     */
    @GetMapping("/product")
    @ResponseBody
    public List<Product> showProduct() {
        return this.service.findAll();
    }

    /**
     * Save new product. If save product was not successful return adminProduct.html with massage,
     * else redirect:/adminProduct.
     *
     * @param product Product
     * @param model Model
     * @return AdminProduct.html or redirect:/adminProduct.
     * @checkstyle ReturnCountCheck (5 lines) The method is allowed to have more than one return.
     */
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute final Product product, final Model model) {
        if (!this.service.saveProduct(product)) {
            model.addAttribute("productMassage", Massage.PRODUCT_NOT_ADD.getMassage());
            return "adminProduct";
        }
        return "redirect:/adminProduct";
    }

    /**
     * Admin gets all products.
     *
     * @param model Model
     * @return AdminProduct.html
     */
    @GetMapping("/adminProduct")
    public static String showProductToAdmin(final Model model) {
        model.addAttribute("product", new Product());
        return "adminProduct";
    }

    /**
     * Changing product.If change product was not successful return adminProduct.html with massage,
     * else redirect:/adminProduct.
     *
     * @param product Product
     * @param model Model
     * @return AdminProduct.html or redirect:/adminProduct.
     * @checkstyle ReturnCountCheck (5 lines) The method is allowed to have more than one return.
     */
    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute final Product product, final Model model) {
        if (!this.service.editProduct(product)) {
            model.addAttribute("editMassage", Massage.INCORRECT_PRODUCT.getMassage());
            return "adminProduct";
        }
        return "redirect:/adminProduct";
    }

}
