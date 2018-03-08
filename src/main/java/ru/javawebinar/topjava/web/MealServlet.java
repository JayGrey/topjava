package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.InMemoryMealDao;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger logger = getLogger(MealServlet.class);
    private final MealDao mealDao;

    public MealServlet() {
        mealDao = new InMemoryMealDao();

        mealDao.addMeal(new Meal(LocalDateTime.of(2018, Month.MARCH, 10, 10, 0), "Завтрак", 500));
        mealDao.addMeal(new Meal(LocalDateTime.of(2018, Month.MARCH, 10, 13, 0), "Обед", 1000));
        mealDao.addMeal(new Meal(LocalDateTime.of(2018, Month.MARCH, 10, 20, 0), "Ужин", 500));
        mealDao.addMeal(new Meal(LocalDateTime.of(2018, Month.MARCH, 11, 10, 0), "Завтрак", 1000));
        mealDao.addMeal(new Meal(LocalDateTime.of(2018, Month.MARCH, 11, 13, 0), "Обед", 500));
        mealDao.addMeal(new Meal(LocalDateTime.of(2018, Month.MARCH, 11, 20, 0), "Ужин", 510));
        mealDao.addMeal(new Meal(LocalDateTime.of(2018, Month.MARCH, 12, 10, 0), "Завтрак", 1000));
        mealDao.addMeal(new Meal(LocalDateTime.of(2018, Month.MARCH, 12, 13, 0), "Обед", 500));
        mealDao.addMeal(new Meal(LocalDateTime.of(2018, Month.MARCH, 12, 20, 0), "Ужин", 600));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("redirect ot meal");

        String action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("delete")) {
            int id = Integer.valueOf(request.getParameter("id"));
            mealDao.deleteMeal(id);
            response.sendRedirect("meals");
            return;
        }

        if (action != null && action.equalsIgnoreCase("update")) {
            int id = Integer.valueOf(request.getParameter("id"));
            request.setAttribute("meal", mealDao.getMealById(id));
            request.getRequestDispatcher("update_meal.jsp").forward(request, response);
            return;
        }

        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(mealDao.getAllMeals(),
                LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        request.setAttribute("mealList", mealWithExceeds);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        request.setAttribute("formatter", formatter);


        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        logger.debug("post attributes: {}", printNiceMap(request.getParameterMap()));

        String action = request.getParameter("action");

        if (action != null && action.equalsIgnoreCase("update")) {
            int id = Integer.valueOf(request.getParameter("id"));
            String description = request.getParameter("name");
            int calories = Integer.valueOf(request.getParameter("calories"));
            LocalDate date = LocalDate.parse(request.getParameter("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime time = LocalTime.parse(request.getParameter("time"), DateTimeFormatter.ISO_TIME);
            Meal meal = new Meal(LocalDateTime.of(date, time), description, calories);
            meal.setId(id);
            mealDao.updateMeal(meal);
        } else {
            String description = request.getParameter("name");
            int calories = Integer.valueOf(request.getParameter("calories"));
            LocalDate date = LocalDate.parse(request.getParameter("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime time = LocalTime.parse(request.getParameter("time"), DateTimeFormatter.ISO_TIME);

            mealDao.addMeal(new Meal(LocalDateTime.of(date, time), description, calories));
        }

        response.sendRedirect("meals");
    }

    private String printNiceMap(Map<String, String[]> map) {
        return map.entrySet().stream()
                .map(es -> es.getKey() + "->" + Arrays.deepToString(es.getValue()))
                .collect(Collectors.joining(";"));
    }
}
