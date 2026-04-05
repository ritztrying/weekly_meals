package com.ritz.weekly_meals.entity;


import com.ritz.weekly_meals.enums.MealDay;
import com.ritz.weekly_meals.enums.MealType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "meal_history")
public class MealHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MealDay mealDay;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private String item;

    private Integer weekNo;
}
