package com.demo.billcalculator.controller;

import com.demo.billcalculator.model.MenuItem;
import com.demo.billcalculator.model.OrderItem;
import com.demo.billcalculator.model.OrderResponse;
import com.demo.billcalculator.repository.MenuItemRepository;
import com.demo.billcalculator.service.OrderResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        BigDecimal totalCost = BigDecimal.ZERO;

        for (Map.Entry<Integer, String> entry : orderItems.entrySet()) {
            MenuItem menuItem = menuItemRepository.findById(entry.getKey()).orElseThrow();
            int quantity = Integer.parseInt(entry.getValue());
            OrderItem orderItem = new OrderItem();
            orderItem.setItemName(menuItem.getName());
            orderItem.setPrice(BigDecimal.valueOf(menuItem.getPrice()));
            orderItem.setQuantity(quantity);
            orderItemList.add(orderItem);
            BigDecimal itemTotal = BigDecimal.valueOf(menuItem.getPrice()).multiply(BigDecimal.valueOf(quantity));
            totalCost = totalCost.add(itemTotal);
        }

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setTotalCost(totalCost.setScale(2, RoundingMode.HALF_UP));
        orderResponse.setOrderItems(orderItemList);

        return orderResponseService.saveOrderResponse(orderResponse);
    }
}