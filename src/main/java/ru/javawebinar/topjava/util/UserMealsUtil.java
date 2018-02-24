package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, List<UserMeal>> userMealMap = new HashMap<>();
        for (UserMeal meal : mealList) {
            userMealMap.putIfAbsent(meal.getDateTime().toLocalDate(), new ArrayList<>());
            userMealMap.get(meal.getDateTime().toLocalDate()).add(meal);
        }

        List<UserMealWithExceed> result = new ArrayList<>();

        for (Map.Entry<LocalDate, List<UserMeal>> entry : userMealMap.entrySet()) {
            int totalCalories = 0;
            boolean exceed = false;

            for (UserMeal meal : entry.getValue()) {
                totalCalories += meal.getCalories();
            }

            if (totalCalories > caloriesPerDay) {
                exceed = true;
            }

            for (UserMeal meal : entry.getValue()) {
                if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                    result.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed));
                }
            }
        }

        return result;
    }
}
