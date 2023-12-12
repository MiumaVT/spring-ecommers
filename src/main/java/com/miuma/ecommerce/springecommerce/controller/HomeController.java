package com.miuma.ecommerce.springecommerce.controller;

import com.miuma.ecommerce.springecommerce.model.Order;
import com.miuma.ecommerce.springecommerce.model.OrderDetails;
import com.miuma.ecommerce.springecommerce.model.Product;
import com.miuma.ecommerce.springecommerce.model.User;
import com.miuma.ecommerce.springecommerce.service.IOrderDetailsService;
import com.miuma.ecommerce.springecommerce.service.IOrderService;
import com.miuma.ecommerce.springecommerce.service.IUserService;
import com.miuma.ecommerce.springecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailsService orderDetailsService;


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
    public String addCart(@RequestParam Integer id, @RequestParam Integer amount, Model model){
        OrderDetails orderDetails = new OrderDetails();
        Product product = new Product();
        double sum = 0;

        Optional<Product> optionalProduct = productService.get(id);
        log.info("Add product: {}", optionalProduct.get());
        log.info("Amount: {}", amount);
        product = optionalProduct.get();

        orderDetails.setAmount(amount);
        orderDetails.setPrice(product.getPrice());
        orderDetails.setName(product.getName());
        orderDetails.setTotal(product.getPrice()*amount);
        orderDetails.setProduct(product);

        //Valide that product doesn't added twice
        Integer idProduct = product.getId();
        boolean joined = details.stream().anyMatch(p -> p.getProduct().getId().equals(idProduct));

        if (! joined) {
            details.add(orderDetails);
        }

        sum = details.stream().mapToDouble(dt -> dt.getTotal()).sum(); //Lamda function

        order.setTotal(sum);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);

        return "user/cart";
    }

    //Remove a product from cart
    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable Integer id, Model model){

        //New list of products
        List<OrderDetails> newOrders = new ArrayList<OrderDetails>();

        for (OrderDetails orderDetails: details){
            if (orderDetails.getProduct().getId()!=id){
                newOrders.add(orderDetails);
            }
        }

        //Put new list with remaining products
        details = newOrders;

        double sum = 0;
        sum = details.stream().mapToDouble(dt -> dt.getTotal()).sum(); //Lamda function

        order.setTotal(sum);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);

        return "user/cart";
    }

    @GetMapping("/getCart")
    public String getCart(Model model){

        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        return "/user/cart";
    }

    @GetMapping("/order")
    public String order(Model model) {

        User user = userService.findById(1).get();

        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        model.addAttribute("user", user);

        return "user/orderresume";
    }

    //Save Order
    @GetMapping("/saveOrder")
    public String saveOrder() {
        Date creationDate = new Date();
        order.setCreationDate(creationDate);
        order.setNumber(orderService.generateOrderNumber());

        //User
        User user = userService.findById(1).get(); //Testing

        order.setUser(user);
        orderService.save(order);

        //Save details
        for (OrderDetails dt:details) {
            dt.setOrder(order);
            orderDetailsService.save(dt);
        }

        //Cleaning list and order
        order = new Order();
        details.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String name, Model model) {
        log.info("Nombre del producto: {}", name);
        List<Product> products = productService.findAll().stream().filter(p -> p.getName().contains(name)).toList();
        model.addAttribute("products", products);

        return "user/home";
    }

}
