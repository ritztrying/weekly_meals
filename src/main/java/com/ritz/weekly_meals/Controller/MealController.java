package com.ritz.weekly_meals.Controller;

import com.ritz.weekly_meals.entity.Meal;
import com.ritz.weekly_meals.enums.MealDay;
import com.ritz.weekly_meals.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/meals")
@CrossOrigin("*")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }

    @GetMapping("/{day}")
    public List<Meal> getMealsByDay(@PathVariable String day) {
        return mealService.findByMealDay(String.valueOf(MealDay.valueOf(day.toUpperCase())));
    }

    @PostMapping
    public Meal saveOrUpdateMeal(@RequestBody Meal meal) {
        return mealService.saveOrUpdateMeal(meal);
    }

    @DeleteMapping("/{id}")
    public void deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
    }

    @PostMapping("/upload")
    public String saveUsingCsv (@RequestParam("file") MultipartFile file) {
        try {
            mealService.saveUsingCSV(file);
            return "Save Sucessfully";
        } catch (Exception ex) {
            return "Error: " + ex.getMessage();
        }
    }
}
