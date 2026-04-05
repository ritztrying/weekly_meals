package com.ritz.weekly_meals.repository;

import com.ritz.weekly_meals.entity.Meal;
import com.ritz.weekly_meals.enums.MealDay;
import com.ritz.weekly_meals.enums.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal,Long> {

    List<Meal> findByMealDay(MealDay mealDay);

    List<Meal> findByWeekNo(Integer weekNo);

    Optional<Meal> findByMealDayAndMealTypeAndWeekNo(MealDay mealDay, MealType mealType, Integer weekNo);

    void deleteByWeekNo(int lastWeek);

    List<Meal> findByWeekNo(int lastWeek);
}
