package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger logger = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("redirect ot meal");

        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2018, Month.MARCH, 10, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 10, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 10, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 11, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 11, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 11, 20, 0), "Ужин", 510),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 12, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 12, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 12, 20, 0), "Ужин", 600)


        );



        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(meals, LocalTime.of(0, 0),
                LocalTime.of(23, 59), 2000);
        request.setAttribute("mealList", mealWithExceeds);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        request.setAttribute("formatter", formatter);


        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
