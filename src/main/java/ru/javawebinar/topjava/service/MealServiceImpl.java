package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    public MealRepository getRepository() {
        return repository;
    }

    @Override
    public Meal save(Integer userId, Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(Integer userId, int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Meal get(Integer userId, int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Meal> getAll() {
        return repository.getAll().stream().collect(Collectors.toList());
    }

    @Override
    public void update(Integer userId, Meal meal) {
        repository.save(meal);
    }
}