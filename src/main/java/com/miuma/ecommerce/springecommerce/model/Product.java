package com.miuma.ecommerce.springecommerce.model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int amount;

    @ManyToOne //This is because ONE user can have many products
    @ToString.Exclude
    private User user;

}
