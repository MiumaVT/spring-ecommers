package com.miuma.ecommerce.springecommerce.model;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private double amount;
    private double price;
    private double total;

    @ManyToOne //This is because in Order details we can see all orders/
    private Order order;

    @ManyToOne //This is because through Order details we can see the products
    private Product product;

}
