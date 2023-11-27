package com.miuma.ecommerce.springecommerce.service;

import com.miuma.ecommerce.springecommerce.model.OrderDetails;

public interface IOrderDetailsService {
    OrderDetails save(OrderDetails orderDetails);
}
