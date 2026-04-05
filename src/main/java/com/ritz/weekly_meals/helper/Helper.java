package com.ritz.weekly_meals.helper;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class Helper {

    // This methode gets current week no of year
    public static int getCurrentWeek() {
        return LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear());
    }
}
