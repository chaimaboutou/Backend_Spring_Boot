package com.demo.billcalculator.service;


import com.demo.billcalculator.model.OrderResponse;
import com.demo.billcalculator.repository.OrderResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderResponseService {

    @Autowired
    private OrderResponseRepository orderResponseRepository;

    public OrderResponse saveOrderResponse(OrderResponse orderResponse) {
        return orderResponseRepository.save(orderResponse);
    }
}