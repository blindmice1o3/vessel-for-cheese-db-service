package com.jackingaming.demo.models;

import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {
    Iterable<Meal> findByOrderByIdDesc();
}
