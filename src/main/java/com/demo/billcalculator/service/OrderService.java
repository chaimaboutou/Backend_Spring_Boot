package com.demo.billcalculator.service;

import com.demo.billcalculator.model.Order;
import com.demo.billcalculator.model.OrderDTO;
import com.demo.billcalculator.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        return orderRepository.save(order);
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderItems(orderDTO.getItems());
        return order;
    }
}

