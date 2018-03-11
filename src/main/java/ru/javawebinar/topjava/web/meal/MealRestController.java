package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public List<Meal> getAll(int userId) {
        return service.getAll(userId);
    }

    public Meal get(int id, int userId) {
        return service.get(id, userId);
    }

    public Meal create(Meal meal, int userId) {
        return service.create(meal, userId);
    }

    public void delete(int id, int userId) {
        service.delete(id, userId);
    }

    public void update(Meal meal, int userId) {
        service.update(meal, userId);

    }

    public List<MealWithExceed> getAllWithExceed(int caloriesPerDay, int userId) {
        List<Meal> meals = service.getAll(userId);
        return MealsUtil.getWithExceeded(meals, caloriesPerDay);
    }

    public List<MealWithExceed> getFilteredWithExceed(int caloriesPerDay, LocalTime startTime, LocalTime endTime,
                                                      int userId) {
        List<Meal> meals = service.getAll(userId);
        return MealsUtil.getFilteredWithExceeded(meals, caloriesPerDay, startTime, endTime);
    }

}