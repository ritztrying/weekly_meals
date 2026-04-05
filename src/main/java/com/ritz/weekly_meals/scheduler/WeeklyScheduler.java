package com.ritz.weekly_meals.scheduler;

import com.ritz.weekly_meals.entity.Meal;
import com.ritz.weekly_meals.entity.MealHistory;
import com.ritz.weekly_meals.repository.MealHistoryRepository;
import com.ritz.weekly_meals.repository.MealRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;

import static com.ritz.weekly_meals.helper.Helper.getCurrentWeek;

@Component
public class WeeklyScheduler {

    @Autowired
    private MealRepository mealRepo;

    @Autowired
    private MealHistoryRepository historyRepo;

    @Transactional
    @Scheduled(cron = "0 0 0 ? * MON")
    public void archiveAndResetMeals() {

        int currentWeek = getCurrentWeek();
        int lastWeek = currentWeek - 1;

        if (lastWeek == 0) {
            lastWeek = LocalDate.now().minusWeeks(1).get(WeekFields.ISO.weekOfWeekBasedYear());
        }

        List<Meal> lastWeekMeals = mealRepo.findByWeekNo(lastWeek);

        if (lastWeekMeals.isEmpty()) {
            System.out.println(" No last week data found");
            return;
        }

        List<MealHistory> historyList = lastWeekMeals.stream().map(m -> {
            MealHistory mealHistory = new MealHistory();
            mealHistory.setMealDay(m.getMealDay());
            mealHistory.setMealType(m.getMealType());
            mealHistory.setItem(m.getItem());
            mealHistory.setWeekNo(m.getWeekNo());
            return mealHistory;
        }).toList();

        historyRepo.saveAll(historyList);

        mealRepo.deleteByWeekNo(lastWeek);

        System.out.println("Archived week " + lastWeek + " data");
    }
}
