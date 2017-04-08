package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

@Controller
public class MealRestController {
    private MealService service;

    public MealService getService() {
        return service;
    }

    public List<MealWithExceed> convertMealToMealExceed(Integer caloriesExceed)
    {
        List<Meal> mealList = service.getAll();

        List<MealWithExceed> mealWithExceeds = MealsUtil.getWithExceeded(mealList, caloriesExceed);

        return mealWithExceeds;
    }
}