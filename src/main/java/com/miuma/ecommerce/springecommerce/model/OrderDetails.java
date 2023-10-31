package com.miuma.ecommerce.springecommerce.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderDetails {

    private Integer id;
    private String name;
    private double amount;
    private double price;
    private double total;

}
