package com.demo.billcalculator.controller;

import com.demo.billcalculator.model.MenuItem;
import com.demo.billcalculator.model.OrderItem;
import com.demo.billcalculator.model.OrderResponse;
import com.demo.billcalculator.repository.MenuItemRepository;
import com.demo.billcalculator.service.OrderResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private OrderResponseService orderResponseService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody Map<Integer, String> orderItems) {
        List<OrderItem> orderItemList = new ArrayList<>();
        double totalCost = 0;

        for (Map.Entry<Integer, String> entry : orderItems.entrySet()) {
            MenuItem menuItem = menuItemRepository.findById(entry.getKey()).orElseThrow();
            int quantity = Integer.parseInt(entry.getValue());

            OrderItem orderItem = new OrderItem();
            orderItem.setItemName(menuItem.getName());
            orderItem.setPrice(menuItem.getPrice());
            orderItem.setQuantity(quantity);

            orderItemList.add(orderItem);
            totalCost += menuItem.getPrice() * quantity;
        }

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setTotalCost(totalCost);
        orderResponse.setOrderItems(orderItemList);

        return orderResponseService.saveOrderResponse(orderResponse);
    }
}