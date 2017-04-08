package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal save(Integer userId, Meal meal);

    void delete(Integer userId, int id) throws NotFoundException;

    Meal get(Integer userId, int id) throws NotFoundException;

    List<Meal> getAll();

    void update(Integer userId, Meal meal);

}