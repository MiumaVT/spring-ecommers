package com.miuma.ecommerce.springecommerce.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Product {

    private Integer id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int amount;

}
