package com.demo.billcalculator.repository;

import com.demo.billcalculator.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
