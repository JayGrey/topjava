package ru.javawebinar.topjava.util;

import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserMealsUtilTest {

    @Test
    public void empty() {
        List<UserMeal> mealList = Collections.emptyList();

        List<UserMealWithExceed> result = UserMealsUtil.getFilteredWithExceeded(mealList, LocalTime.MIDNIGHT, LocalTime.NOON, 100);
        assertEquals(0, result.size());
    }

    @Test
    public void timeWithSameDate() {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2018, 2, 24, 0, 0), "meal 1", 100),
                new UserMeal(LocalDateTime.of(2018, 2, 24, 9, 0), "meal 2", 200),
                new UserMeal(LocalDateTime.of(2018, 2, 24, 12, 0), "meal 2", 300)
        );

        List<UserMealWithExceed> result = UserMealsUtil.getFilteredWithExceeded(meals, LocalTime.of(9, 0), LocalTime.of(12, 0), 0);
        assertEquals(2, result.size());
    }

    @Test
    public void timeWithDifferentDate() {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2018, 2, 24, 0, 0), "meal 1", 100),
                new UserMeal(LocalDateTime.of(2018, 2, 25, 9, 0), "meal 2", 200),
                new UserMeal(LocalDateTime.of(2018, 2, 26, 12, 0), "meal 2", 300)
        );

        List<UserMealWithExceed> result = UserMealsUtil.getFilteredWithExceeded(meals, LocalTime.of(9, 0), LocalTime.of(12, 0), 0);
        assertEquals(2, result.size());
    }

    @Test
    public void exceedInSameDate() {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2018, 2, 24, 0, 0), "meal 1", 100),
                new UserMeal(LocalDateTime.of(2018, 2, 24, 9, 0), "meal 2", 200),
                new UserMeal(LocalDateTime.of(2018, 2, 24, 12, 0), "meal 3", 300)
        );

        List<UserMealWithExceed> result = UserMealsUtil.getFilteredWithExceeded(meals, LocalTime.of(9, 0), LocalTime.of(12, 0), 500);

        assertTrue(result.get(0).isExceed());
    }

    @Test
    public void exceedInDifferentDates() {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2018, 2, 24, 0, 0), "meal 1", 100),
                new UserMeal(LocalDateTime.of(2018, 2, 25, 9, 0), "meal 2", 200)
        );

        List<UserMealWithExceed> result = UserMealsUtil.getFilteredWithExceeded(meals, LocalTime.of(9, 0), LocalTime.of(12, 0), 100);

        assertTrue(result.get(0).isExceed());
    }

    @Test
    public void notExceed() {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2018, 2, 24, 0, 0), "meal 1", 100),
                new UserMeal(LocalDateTime.of(2018, 2, 24, 9, 0), "meal 2", 200)
        );

        List<UserMealWithExceed> result = UserMealsUtil.getFilteredWithExceeded(meals, LocalTime.of(9, 0), LocalTime.of(12, 0), 300);

        assertEquals(1, result.size());
        assertFalse(result.get(0).isExceed());
    }

    @Test
    public void notExceedDifferentDates() {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2018, 2, 24, 9, 0), "meal 1", 100),
                new UserMeal(LocalDateTime.of(2018, 2, 25, 15, 0), "meal 2", 200)
        );

        List<UserMealWithExceed> result = UserMealsUtil.getFilteredWithExceeded(meals, LocalTime.of(9, 0), LocalTime.of(12, 0), 100);

        assertEquals(1, result.size());
        assertFalse(result.get(0).isExceed());
    }
}