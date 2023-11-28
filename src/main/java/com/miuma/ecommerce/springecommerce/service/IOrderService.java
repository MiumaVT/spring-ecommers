package com.miuma.ecommerce.springecommerce.service;

import com.miuma.ecommerce.springecommerce.model.Order;

import java.util.List;

public interface IOrderService {

    List<Order> findAll();
    Order save(Order order);

    String generateOrderNumber();
}
