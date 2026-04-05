package com.ritz.weekly_meals.helper;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class Helper {

    public static int getCurrentWeek() {
        return LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear());
    }
}
