package com.miuma.ecommerce.springecommerce.model;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class User {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private String addres;
    private String phone;
    private String type;
    private String password;



}
