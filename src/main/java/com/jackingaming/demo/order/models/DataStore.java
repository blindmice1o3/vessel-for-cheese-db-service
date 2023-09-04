package com.jackingaming.demo.order.models;

import java.util.List;

public class DataStore {
    private List<Order> data;

    public DataStore() {
    }

    public DataStore(List<Order> data) {
        this.data = data;
    }

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}
