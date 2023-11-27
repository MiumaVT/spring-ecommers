package com.miuma.ecommerce.springecommerce.service;

import com.miuma.ecommerce.springecommerce.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements IOrderService{

    @Autowired
    private IOrderService orderService;

    @Override
    public Order save(Order order) {
        return orderService.save(order);
    }
}
