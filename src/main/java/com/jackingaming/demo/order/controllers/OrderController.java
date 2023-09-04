package com.jackingaming.demo.order.controllers;

import com.jackingaming.demo.order.models.DataStore;
import com.jackingaming.demo.order.models.MenuItemInfoListWrapper;
import com.jackingaming.demo.order.models.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderRepository repository;

    private List<MenuItemInfoListWrapper> menuItemInfoListWrappersLocal = new ArrayList<>();

    @GetMapping(path = "/all",
            produces = "application/json")
    public DataStore getAllOrders() {
        System.out.println("getAllOrders()");

        return new DataStore(menuItemInfoListWrappersLocal);
    }

    @PostMapping(path = "/append",
            consumes = "application/json",
            produces = "application/json")
    public MenuItemInfoListWrapper appendNewOrder(@RequestBody MenuItemInfoListWrapper menuItemInfoListWrapper) {
        System.out.println("appendNewOrder()");

        menuItemInfoListWrappersLocal.add(menuItemInfoListWrapper);
        LocalDateTime createdOn = menuItemInfoListWrapper.getCreatedOn();
        System.out.println("createdOn: " + createdOn.toString());

        return menuItemInfoListWrapper;
    }
}
