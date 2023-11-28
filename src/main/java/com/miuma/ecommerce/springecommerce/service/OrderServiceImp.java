package com.miuma.ecommerce.springecommerce.service;

import com.miuma.ecommerce.springecommerce.model.Order;
import com.miuma.ecommerce.springecommerce.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements IOrderService{

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public String generateOrderNumber() {

        int number = 0;
        String concatenatedNumber = "";

        List<Order> orders = findAll();

        List<Integer> numbers = new ArrayList<Integer>();

        orders.stream().forEach(o -> numbers.add(Integer.parseInt(o.getNumber())));

        if(orders.isEmpty()) {
            number = 1;
        }else {
            number = numbers.stream().max(Integer::compareTo).get();
            number++;
        }

        if (number<10) {
            concatenatedNumber = "000000000" + String.valueOf(number);
        }else if (number<100) {
            concatenatedNumber = "00000000" + String.valueOf(number);
        }else if (number<1000) {
            concatenatedNumber = "0000000" + String.valueOf(number);
        }else if (number<10000) {
            concatenatedNumber = "000000" + String.valueOf(number);
        }

        return concatenatedNumber;
    }
}
