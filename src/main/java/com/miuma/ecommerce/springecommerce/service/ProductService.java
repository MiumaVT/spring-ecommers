package com.miuma.ecommerce.springecommerce.service;

import com.miuma.ecommerce.springecommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
//CRUD method
    public Product save(Product product);
    public Optional<Product> get(Integer id); //Optional give us a validation if that product exists on database
    public void update(Product product);
    public void delete(Integer id);
    public List<Product> findAll();
}
