package com.ritz.weekly_meals.entity;

import com.ritz.weekly_meals.enums.MealDay;
import com.ritz.weekly_meals.enums.MealType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(
            columnNames = {"meal_day", "meal_type", "week_no"}
    )
)
public class Meal {

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
