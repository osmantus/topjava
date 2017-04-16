package ru.javawebinar.topjava;

import org.junit.Test;
import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

public class MealTestData {

    public static final int MEAL_ID = BaseEntity.START_SEQ + 2;

    public final Meal meal1 = new Meal(MEAL_ID, LocalDateTime.of(2017, Month.APRIL, 16, 8, 0, 0), "meal1", 700);
    public final Meal meal2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2017, Month.APRIL, 16, 14, 0, 0), "meal2", 400);
    public final Meal meal3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2017, Month.APRIL, 16, 19, 0, 0), "meal3", 400);

    public ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getDate(), actual.getDate())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                    )
    );

    @Test
    public void MealTest() throws Exception {
        MATCHER = new ModelMatcher<>(
                (expected, actual) -> expected == actual ||
                        (Objects.equals(expected.getDate(), actual.getDate())
                                && Objects.equals(expected.getId(), actual.getId())
                                && Objects.equals(expected.getDescription(), actual.getDescription())
                                && Objects.equals(expected.getCalories(), actual.getCalories())
                        )
        );

        MATCHER.assertEquals(meal1, meal1);
        MATCHER.assertEquals(meal2, meal2);
    }
}
