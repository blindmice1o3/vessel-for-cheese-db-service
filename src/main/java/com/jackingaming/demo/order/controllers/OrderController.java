package com.jackingaming.demo.order.controllers;

import com.jackingaming.demo.order.models.DataStore;
import com.jackingaming.demo.order.models.Order;
import com.jackingaming.demo.order.models.legacy.OrderOldVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderOldVersionRepository repository;

    private List<Order> orders = new ArrayList<>();

    @GetMapping(path = "/all",
            produces = "application/json")
    public DataStore getAllOrders() {
        System.out.println("getAllOrders()");

        return new DataStore(orders);
    }

    @PostMapping(path = "/append",
            consumes = "application/json",
            produces = "application/json")
    public Order appendNewOrder(@RequestBody Order order) {
        System.out.println("appendNewOrder()");

        orders.add(order);
        LocalDateTime createdOn = order.getCreatedOn();
        System.out.println("createdOn: " + createdOn.toString());

        return order;
    }
}
