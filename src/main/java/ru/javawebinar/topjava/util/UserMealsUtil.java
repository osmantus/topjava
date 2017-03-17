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
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        System.out.println("TODO return filtered list with correctly exceeded field");

        List<UserMealWithExceed> resultedList = new ArrayList<>();
        Map<LocalDate, Integer> totalMealCaloriesByDate = new HashMap<>();
        Map<LocalDate, List<UserMeal>> mealsByDate = new HashMap<>();
        List<UserMeal> userMeals = null;

        for (UserMeal eachMeal : mealList)
        {
            LocalDate mealDate = eachMeal.getDateTime().toLocalDate();
            if (!totalMealCaloriesByDate.containsKey(mealDate))
            {
                totalMealCaloriesByDate.put(mealDate, eachMeal.getCalories());
                userMeals = new ArrayList<>();
                userMeals.add(eachMeal);
                mealsByDate.put(mealDate, userMeals);
            }
            else
            {
                int currentTotalCalories = totalMealCaloriesByDate.get(mealDate);
                totalMealCaloriesByDate.put(mealDate, currentTotalCalories + eachMeal.getCalories());

                if (mealsByDate.containsKey(mealDate))
                {
                    userMeals = mealsByDate.get(mealDate);
                    userMeals.add(eachMeal);
                    mealsByDate.put(mealDate, userMeals);
                }
            }

        }

        for (Map.Entry<LocalDate, Integer> eachDate : totalMealCaloriesByDate.entrySet())
        {
            LocalDate date = eachDate.getKey();
            Integer totalCaloriesExeeded = eachDate.getValue();
            if (totalCaloriesExeeded > caloriesPerDay)
            {
                if (mealsByDate.containsKey(date))
                {
                    List<UserMeal> filteredMealList = mealsByDate.get(date);
                    if (!filteredMealList.isEmpty())
                    {
                        for (UserMeal eachMeal : filteredMealList)
                        {
                            LocalTime mealTime = eachMeal.getDateTime().toLocalTime();
                            if (mealTime.isAfter(startTime) && mealTime.isBefore(endTime))
                            {
                                if (eachMeal.getDateTime().toLocalDate().equals(date))
                                    resultedList.add(new UserMealWithExceed(eachMeal.getDateTime(), eachMeal.getDescription(), eachMeal.getCalories(), true));
                            }
                        }
                    }
                }
            }
        }

        return resultedList;
    }
}
