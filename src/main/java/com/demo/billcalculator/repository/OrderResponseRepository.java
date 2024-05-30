package com.demo.billcalculator.repository;


import com.demo.billcalculator.model.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderResponseRepository extends JpaRepository<OrderResponse, Long> {
}
