package com.miuma.ecommerce.springecommerce.controller;

import com.miuma.ecommerce.springecommerce.model.Product;
import com.miuma.ecommerce.springecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String home(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "admin/home";
    }

}
