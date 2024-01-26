package com.miuma.ecommerce.springecommerce.controller;

import com.miuma.ecommerce.springecommerce.model.Product;
import com.miuma.ecommerce.springecommerce.model.User;
import com.miuma.ecommerce.springecommerce.service.IUserService;
import com.miuma.ecommerce.springecommerce.service.ProductService;
import com.miuma.ecommerce.springecommerce.service.UploadFileService;
import com.miuma.ecommerce.springecommerce.service.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    //This method will show us by console information about product will save on ecommerce
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private UploadFileService upload;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("products", productService.findAll()); // This method can show from backend to
        //frontend. Model.addAttribute recives two parameters. attributeName(name), and the class where recives that information
        return "products/show";
    }

    @GetMapping("/create")
    public String create(){
        return "products/create";
    }

    @PostMapping("/save")
    public String save(Product product, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        LOGGER.info("This is the object product {}", product);


        User u = userService.findById(Integer.parseInt(session.getAttribute("iduser").toString())).get();
        product.setUser(u);

        //Image
        if(product.getId()==null){ //When is created a product
            String imgName = upload.saveImage(file);
            product.setImage(imgName);
        }

        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();

        LOGGER.info("Sought product: {}", product);
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/update")
    public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException {
        Product p = new Product();
        p=productService.get(product.getId()).get();

        if(file.isEmpty()){ //Edit product without change image
            product.setImage(p.getImage());
        }else {//When imagen even edit
            //Delete when the images ist'n by default
            if (!p.getImage().equals("default.jpg")){
                upload.deleteImage(p.getImage());
            }

            String imgName = upload.saveImage(file);
            product.setImage(imgName);
        }
        product.setUser(p.getUser());
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Product p = new Product();
        p=productService.get(id).get();

        //Delete when the images ist'n by default
        if (p.getImage().equals("default.jpg")){
            upload.deleteImage(p.getImage());
        }

        productService.delete(id);
        return "redirect:/products";
    }

}
