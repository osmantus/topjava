package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = ModelMatcher.of(Meal.class);

    public static final int MEAL1_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 8;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, of(2015, Month.MAY, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final Meal ADMIN_MEAL1 = new Meal(ADMIN_MEAL_ID, of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL2 = new Meal(ADMIN_MEAL_ID + 1, of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final List<Meal> MEALS = Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static final MealWithExceed MEAL1_EX = MealsUtil.createWithExceed(MEAL1, false);
    public static final MealWithExceed MEAL2_EX = MealsUtil.createWithExceed(MEAL2, false);
    public static final MealWithExceed MEAL3_EX = MealsUtil.createWithExceed(MEAL3, false);
    public static final MealWithExceed MEAL4_EX = MealsUtil.createWithExceed(MEAL4, true);
    public static final MealWithExceed MEAL5_EX = MealsUtil.createWithExceed(MEAL5, true);
    public static final MealWithExceed MEAL6_EX = MealsUtil.createWithExceed(MEAL6, true);

    public static final List<MealWithExceed> MEALS_EX = Arrays.asList(MEAL6_EX, MEAL5_EX, MEAL4_EX, MEAL3_EX, MEAL2_EX, MEAL1_EX);

    public static Meal getCreated() {
        return new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Созданный ужин", 300);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL1.getDateTime(), "Обновленный завтрак", 200);
    }

    public static final ModelMatcher<MealWithExceed> MEAL_WITH_EXCEED_MODEL_MATCHER = ModelMatcher.of(MealWithExceed.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                            && Objects.equals(expected.isExceed(), actual.isExceed())
                    )
    );

}
