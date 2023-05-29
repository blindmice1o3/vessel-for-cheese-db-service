package com.jackingaming.demo.controllers;

import com.jackingaming.demo.models.Meal;
import com.jackingaming.demo.models.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("meals")
public class MealController {
    @Autowired
    private MealRepository mealRepository;

    @PostMapping(path = "/add")
    public String addNewMeal(@RequestParam String name,
                             @RequestParam Integer size,
                             @RequestParam String drink) {
        Meal meal = new Meal();
        meal.setName(name);
        meal.setSize(size);
        meal.setDrink(drink);
        Meal mealWithId = mealRepository.save(meal);

        return Long.toString(mealWithId.getId());
    }

    @GetMapping(path = "/all")
    public Iterable<Meal> getAllMeals() {
//        return mealRepository.findAll();
        return mealRepository.findByOrderByIdDesc();
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        if (mealRepository.existsById(id)) {
            System.out.printf("MealController.deleteById(Long) entry of provided id (%d) does EXIST in mealRepository", id);
            mealRepository.deleteById(id);
            System.out.printf("MealController.deleteById(Long) entry of provided id (%d) has been DELETED from mealRepository", id);
            return "yes";
        } else {
            System.out.printf("MealController.deleteById(Long) entry of provided id (%d) does NOT EXIST in repository", id);
            return "no";
        }
    }
}
