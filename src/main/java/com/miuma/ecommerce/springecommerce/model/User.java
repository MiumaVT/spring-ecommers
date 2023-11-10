package com.miuma.ecommerce.springecommerce.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This will be the ID variable be autoincrement

    private Integer id;
    private String name;
    private String username;
    private String email;
    private String addres;
    private String phone;
    private String type;
    private String password;

    @OneToMany(mappedBy = "user") //This is because the table Products are mapped by user table.
    private List<Product> products;

    @OneToMany(mappedBy = "user") //This anotation is because the table order is mapped by user table.
    private List<Order> orders;

    public User() {
    }

    public User(Integer id, String name, String username, String email, String addres, String phone, String type, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.addres = addres;
        this.phone = phone;
        this.type = type;
        this.password = password;
    }
}
