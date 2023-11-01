package com.miuma.ecommerce.springecommerce.repository;

import com.miuma.ecommerce.springecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {//JpaRepository<(Class that INHERITS), Type data(Integer because is Id)>
}
