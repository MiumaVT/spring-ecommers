package com.miuma.ecommerce.springecommerce.model;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Order {

    private Integer id;
    private String number;
    private Date creationDate;
    private Date receiveDate;

    private double total;

}
