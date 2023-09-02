package com.jackingaming.demo.meal.models;

import jakarta.persistence.*;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private Integer size;
    private String drink;

    public Meal() {}

    @Override
    public String toString() {
        return String.format(
                "Meal[id=%d, name='%s', size=%d, drink='%s']",
                id, name, size, drink);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}
