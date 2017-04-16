package ru.javawebinar.topjava;

import org.junit.Test;
import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

public class MealTestData {

    public final Meal meal1 = new Meal(1, LocalDateTime.of(2017, Month.APRIL, 16, 8, 0, 0), "meal1", 700);
    public final Meal meal2 = new Meal(2, LocalDateTime.of(2017, Month.APRIL, 16, 14, 0, 0), "meal2", 400);
    public final Meal meal3 = new Meal(3, LocalDateTime.of(2017, Month.APRIL, 16, 19, 0, 0), "meal3", 400);

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
