package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

public class MealsUtil {

    //public static List<MealWithExceed> mealsStaticList = new ArrayList<>();

    public static Integer DEFAULT_CALORIES_PER_DAY = 2000;

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

    public static List<MealWithExceed> getFilteredWithExceeded(Collection<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    /*public static List<MealWithExceed> createMealsExceedStaticList()
    {
        mealsStaticList = MEALS.stream()
                .map(meal -> new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), false))
                .collect(Collectors.toList());

        return mealsStaticList;
    }*/

    public static List<MealWithExceed> getWithExceeded(Collection<Meal> mealList, int caloriesPerDay)
    {
        return getFilteredWithExceeded(mealList, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }
}