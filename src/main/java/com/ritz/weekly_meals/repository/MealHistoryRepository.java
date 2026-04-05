package com.ritz.weekly_meals.repository;

import com.ritz.weekly_meals.entity.MealHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealHistoryRepository extends JpaRepository<MealHistory, Long> {
}
