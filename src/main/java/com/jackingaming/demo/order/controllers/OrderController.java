package com.jackingaming.demo.order.controllers;

import com.jackingaming.demo.order.models.Order;
import com.jackingaming.demo.order.models.OrderDTO;
import com.jackingaming.demo.order.models.legacy.OrderOldVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1")
public class OrderController {
    @Autowired
    private OrderOldVersionRepository repository;

    private List<Order> orders = new ArrayList<>();
    private long counter = 0L;

    @GetMapping(path = "/orders",
            produces = "application/json")
    private OrderDTO fetchNewerOrders(@RequestParam String timestamp) {
        System.out.println("fetchNewerOrders(String)");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime timestampNewestClient = LocalDateTime.parse(timestamp, formatter);
        System.out.println("timestampNewestClient: " + timestampNewestClient);

        List<Order> ordersNewerThanClient = new ArrayList<>();
        if (!orders.isEmpty()) {
            System.out.println("orders NOT EMPTY... find indexFrom");

            // find first index that is newer than client's most recent timestamp.
            int indexFrom = -1;
            int indexEnd = orders.size() - 1;
            LocalDateTime timestampLocal = orders.get(indexEnd).getCreatedOn();
            if (timestampNewestClient.isBefore(timestampLocal)) {
                System.out.println("timestampNewestClient.isBefore(timestampLocal)");

                // start at last element (end of list)
                for (int i = indexEnd; i >= 0; i--) {
                    timestampLocal = orders.get(i).getCreatedOn();

                    if (timestampNewestClient.isBefore(timestampLocal)) {
                        if (i == 0) {
                            indexFrom = 0;
                            break;
                        }

                        continue;
                    }

                    indexFrom = i + 1;
                    break;
                }

                ordersNewerThanClient.addAll(
                        orders.subList(indexFrom, orders.size())
                );
            } else {
                System.out.println("NOT timestampNewestClient.isBefore(timestampLocal)... do nothing");
            }
        } else {
            System.out.println("orders EMPTY... do nothing");
        }

        return new OrderDTO(ordersNewerThanClient);
    }

    @PostMapping(path = "/orders",
            consumes = "application/json",
            produces = "application/json")
    private Order appendNewOrder(@RequestBody Order orderFromClient) {
        System.out.println("appendNewOrder(Order)");

        orderFromClient.setId(++counter);

        LocalDateTime timestampClient = orderFromClient.getCreatedOn();
        System.out.println("timestampClient: " + timestampClient.toString());

        if (!orders.isEmpty()) {
            System.out.println("orders NOT EMPTY... find where to be inserted");

            // sort by timestamp (old-to-new)
            // (first element is oldest, last element is newest)
            // start at last element (end of list)
            int indexEnd = orders.size() - 1;
            for (int i = indexEnd; i >= 0; i--) {
                LocalDateTime timestampLocal = orders.get(i).getCreatedOn();

                if (timestampClient.isBefore(timestampLocal)) {
                    if (i == 0) {
                        orders.add(0, orderFromClient);
                        break;
                    }

                    continue;
                }

                orders.add(i + 1, orderFromClient);
                break;
            }
        } else {
            System.out.println("orders EMPTY... insert into list");

            orders.add(orderFromClient);
        }

        return orderFromClient;
    }

    @DeleteMapping("/orders/{id}")
    private void deleteOrder(@PathVariable Long id) {
        System.out.println("deleteOrder(Long)");

        Order orderToRemove = null;
        for (Order order : orders) {
            if (order.getId() == id) {
                orderToRemove = order;
                break;
            }
        }
        orders.remove(orderToRemove);
    }
}
