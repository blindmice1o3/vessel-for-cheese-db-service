package com.jackingaming.demo.meal.models;

import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {
    Iterable<Meal> findByOrderByIdDesc();
}
