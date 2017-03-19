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

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> resultedList = new ArrayList<>();
        Map<LocalDate, Integer> totalMealCaloriesByDate = new HashMap<>();
        Map<LocalDate, List<UserMeal>> mealsByDate = new HashMap<>();
        List<UserMeal> userMeals = null;
        List<UserMeal> allFilteredMeals = new ArrayList<>();

        for (UserMeal eachMeal : mealList) {
            LocalDate mealDate = eachMeal.getDateTime().toLocalDate();
            LocalTime mealTime = eachMeal.getDateTime().toLocalTime();

            int currentTotalCalories = totalMealCaloriesByDate.getOrDefault(mealDate, 0);

            totalMealCaloriesByDate.put(mealDate, currentTotalCalories + eachMeal.getCalories());

            if (mealTime.isAfter(startTime) && mealTime.isBefore(endTime)) {
                userMeals = mealsByDate.getOrDefault(mealDate, new ArrayList<UserMeal>());
                userMeals.add(eachMeal);
                mealsByDate.put(mealDate, userMeals);
            }
        }

        for (Map.Entry<LocalDate, List<UserMeal>> eachDate : mealsByDate.entrySet()) {
            LocalDate date = eachDate.getKey();

            if (totalMealCaloriesByDate.containsKey(date))
            {
                Integer totalCaloriesExeeded = totalMealCaloriesByDate.get(date);
                if (totalCaloriesExeeded > caloriesPerDay) {
                    allFilteredMeals.addAll(eachDate.getValue());
                }
            }
        }

        allFilteredMeals.forEach(item -> resultedList.add(new UserMealWithExceed(item.getDateTime(), item.getDescription(), item.getCalories(), true)));

        return resultedList;
    }
}
