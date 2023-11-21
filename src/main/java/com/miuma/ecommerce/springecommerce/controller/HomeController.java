package com.miuma.ecommerce.springecommerce.controller;

import com.miuma.ecommerce.springecommerce.model.Order;
import com.miuma.ecommerce.springecommerce.model.OrderDetails;
import com.miuma.ecommerce.springecommerce.model.Product;
import com.miuma.ecommerce.springecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductService productService;

    //To store details on the order
    List<OrderDetails> details = new ArrayList<OrderDetails>();

    //Order details
    Order order = new Order();

    @GetMapping("")
    public String home(Model model){
        model.addAttribute("products", productService.findAll());

        return "user/home";
    }

    @GetMapping("homeproduct/{id}")
    public String homeProduct(@PathVariable Integer id, Model model){
        log.info("product Id sent like parameter {}", id);
        Product product = new Product();
        Optional<Product> productOptional = productService.get(id);
        product = productOptional.get();

        model.addAttribute("product", product);

        return "user/homeproduct";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer amount){
        OrderDetails orderDetails = new OrderDetails();
        Product product = new Product();
        double sum = 0;

        Optional<Product> optionalProduct = productService.get(id);
        log.info("Add product: {}", optionalProduct.get());
        log.info("Amount: {}", amount);

        return "user/cart";
    }

}
