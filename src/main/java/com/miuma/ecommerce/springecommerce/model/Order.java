package com.miuma.ecommerce.springecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String number;
    private Date creationDate;
    private Date receiveDate;

    private double total;

    @ManyToOne //This is because ONE user can have many orders.
    private User user;

    @OneToOne(mappedBy = "order") //This is because the order will have only one OrderDetails.
    private OrderDetails details;

}
