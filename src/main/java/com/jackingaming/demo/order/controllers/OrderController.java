package com.jackingaming.demo.order.controllers;

import com.jackingaming.demo.order.models.MenuItemInfo;
import com.jackingaming.demo.order.models.MenuItemInfoListWrapper;
import com.jackingaming.demo.order.models.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderRepository repository;

    private List<MenuItemInfo> menuItemInfosLocal = new ArrayList<>();

    @GetMapping(path = "/all",
            produces = "application/json")
    public MenuItemInfoListWrapper getAllOrders() {
        System.out.println("getAllOrders()");
    
        return new MenuItemInfoListWrapper(menuItemInfosLocal);
    }

    @PostMapping(path = "/append",
            consumes = "application/json",
            produces = "application/json")
    public MenuItemInfoListWrapper appendNewOrder(@RequestBody MenuItemInfoListWrapper menuItemInfoListWrapper) {
        System.out.println("appendNewOrder()");

        List<MenuItemInfo> menuItemInfosFromRequestBody = menuItemInfoListWrapper.getMenuItemInfos();
        menuItemInfosLocal.addAll(menuItemInfosFromRequestBody);

        // TODO: remove below
        for (MenuItemInfo menuItemInfo : menuItemInfosFromRequestBody) {
            System.out.println("$ " + menuItemInfo.getId() + " [" + menuItemInfo.getSize() + "]");
            for (String customization : menuItemInfo.getMenuItemCustomizations()) {
                System.out.println("  * " + customization);
            }
        }

        return new MenuItemInfoListWrapper(menuItemInfosLocal);
    }
}
