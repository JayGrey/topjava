package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

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
                if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                    totalCalories += meal.getCalories();
                }
                if (totalCalories > caloriesPerDay) {
                    exceed = true;
                    break;
                }
            }

            for (UserMeal meal : entry.getValue()) {
                result.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed));
            }
        }

        return result;
    }
}
