package com.demo.billcalculator.model;
import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection
    @CollectionTable(name = "order_item_details", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "item_id")
    @Column(name = "quantity")
    private Map<Integer, String> orderItems;

    public Order() {
        this.orderItems = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer, String> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<Integer, String> orderItems) {
        this.orderItems = orderItems;
    }
}
