package com.miuma.ecommerce.springecommerce.controller;

import com.miuma.ecommerce.springecommerce.model.Order;
import com.miuma.ecommerce.springecommerce.model.User;
import com.miuma.ecommerce.springecommerce.service.IOrderService;
import com.miuma.ecommerce.springecommerce.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    //          /user/registration
    @GetMapping("/registration")
    public String create() {
        return "/user/registration";
    }

    @PostMapping("/save")
    public String save(User user) {
        logger.info("Usuario registrado: {}", user);
        user.setType("USER");
        userService.save(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/access")
    public String access(User user, HttpSession session){
        logger.info("Accesos: {}", user);

        Optional<User> userGet = userService.findByEmail(user.getEmail());
        //logger.info("Usuario de db: {}", userGet.get());

        if(userGet.isPresent()){
            session.setAttribute("iduser", userGet.get().getId());
            if(userGet.get().getType().equals("ADMIN")) {
                return "redirect:/admin";
            }else {
                return "redirect:/";
            }
        }else {
            logger.info("Usuario no existe");
        }

        return "redirect:/";
    }

    @GetMapping("/shopping")
    public String getShopping(Model model, HttpSession session){
        model.addAttribute("session", session.getAttribute("iduser"));

        User user = userService.findById(Integer.parseInt(session.getAttribute("iduser").toString())).get();
        List<Order> orders = orderService.findByUser(user);

        model.addAttribute("orders", orders);

        return "user/shopping";
    }

    @GetMapping("/detail/{id}")
    public String shoppingDetails(@PathVariable Integer id, HttpSession session, Model model){
        logger.info("Id de la orden: {}", id);
        Optional<Order> order = orderService.findById(id);

        model.addAttribute("details", order.get().getDetails());

        //session
        model.addAttribute("session", session.getAttribute("iduser"));
        return "user/shoppingdetails";
    }

    @GetMapping("logout")
    public String logOut(HttpSession session){
        session.removeAttribute("iduser");

        logger.info("Esta cerrando la sesion");

        return "redirect:/";
    }

}
