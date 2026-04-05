package com.ritz.weekly_meals.service;

import com.ritz.weekly_meals.entity.Meal;
import com.ritz.weekly_meals.enums.MealDay;
import com.ritz.weekly_meals.enums.MealType;
import com.ritz.weekly_meals.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import static com.ritz.weekly_meals.helper.Helper.getCurrentWeek;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public List<Meal> getAllMeals() {
        Integer currentWeekNo = getCurrentWeek();
        return mealRepository.findByWeekNo(currentWeekNo);
    }

    public List<Meal> findByMealDay(@PathVariable String day) {
        return mealRepository.findByMealDay(MealDay.valueOf(day.toUpperCase()));
    }

    public Meal saveOrUpdateMeal(@RequestBody Meal meal) {
        int weekNo = getCurrentWeek();
        meal.setWeekNo(weekNo);

        Optional<Meal> existing = mealRepository.findByMealDayAndMealTypeAndWeekNo(
                meal.getMealDay(),
                meal.getMealType(),
                weekNo);

        if (existing.isPresent()) {
            Meal m = existing.get();
            m.setItem(meal.getItem());
            return mealRepository.save(m);
        }

        return mealRepository.save(meal);
    }

    public void deleteMeal(@PathVariable Long id) {
        mealRepository.deleteById(id);
    }

    public void saveUsingCSV(MultipartFile file) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        String line;
        boolean isFirstLine = true;

        int currentWeek = getCurrentWeek();

        while ((line = reader.readLine()) != null) {

            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            try {
                String[] data = line.split(",");

                if (data.length < 3) continue;

                MealDay mealDay = MealDay.valueOf(data[0].trim().toUpperCase());
                MealType mealType = MealType.valueOf(data[1].trim().toUpperCase());
                String item = data[2].trim();

                Optional<Meal> existing = mealRepository.findByMealDayAndMealTypeAndWeekNo(
                        mealDay,
                        mealType,
                        currentWeek);

                if (existing.isPresent()) {
                    Meal meal = existing.get();
                    meal.setItem(item);
                    mealRepository.save(meal);
                } else {
                    Meal meal = new Meal();
                    meal.setMealDay(mealDay);
                    meal.setMealType(mealType);
                    meal.setItem(item);
                    meal.setWeekNo(currentWeek);
                    mealRepository.save(meal);
                }

            } catch (Exception e) {
                System.out.println("Error in line: " + line);
            }
        }
    }

}
