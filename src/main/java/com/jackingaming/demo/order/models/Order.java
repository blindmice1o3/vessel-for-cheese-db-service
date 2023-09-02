package com.jackingaming.demo.order.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nameDrink;
    private int sizeDrink;

    public Order() {
    }

    public Order(String nameDrink, int sizeDrink) {
        this.nameDrink = nameDrink;
        this.sizeDrink = sizeDrink;
    }

    public String getNameDrink() {
        return nameDrink;
    }

    public void setNameDrink(String nameDrink) {
        this.nameDrink = nameDrink;
    }

    public int getSizeDrink() {
        return sizeDrink;
    }

    public void setSizeDrink(int sizeDrink) {
        this.sizeDrink = sizeDrink;
    }
}
