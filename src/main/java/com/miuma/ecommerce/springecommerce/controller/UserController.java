package com.miuma.ecommerce.springecommerce.controller;

import com.miuma.ecommerce.springecommerce.model.User;
import com.miuma.ecommerce.springecommerce.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

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

}
