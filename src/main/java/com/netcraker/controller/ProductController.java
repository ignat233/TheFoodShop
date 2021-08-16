/*
 * Copyright
 */

package com.netcraker.controller;

import com.netcraker.controller.massage.Massage;
import com.netcraker.model.Order;
import com.netcraker.model.Product;
import com.netcraker.services.ProductService;
import java.util.List;
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
    private final ProductService service;

    /**
     * Dependency injection through the constructor.
     *
     * @param service ProductService
     */
    ProductController(final ProductService service) {
        this.service = service;
    }

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
     */
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute final Product product, final Model model) {
        final String page;
        if (this.service.saveProduct(product)) {
            page = "redirect:/adminProduct";
        } else {
            model.addAttribute("productMassage", Massage.PRODUCT_NOT_ADD.getMassage());
            page = "adminProduct";
        }
        return page;
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
     */
    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute final Product product, final Model model) {
        final String page;
        if (this.service.editProduct(product)) {
            page = "redirect:/adminProduct";
        } else {
            model.addAttribute("editMassage", Massage.INCORRECT_PRODUCT.getMassage());
            page = "adminProduct";
        }
        return page;
    }

}
