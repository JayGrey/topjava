package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

`import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {

        return mealList.stream()
                .collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate()))
                .values().stream()
                .flatMap(ml -> proceedOneDay(ml, startTime, endTime, caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static Stream<UserMealWithExceed> proceedOneDay(List<UserMeal> list, LocalTime startTime, LocalTime endTime, int maxCalories) {
        int totalCalories = list.stream()
                .mapToInt(UserMeal::getCalories)
                .sum();

        return list.stream()
                .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime))
                .map(m -> new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(), totalCalories > maxCalories));
    }
}
