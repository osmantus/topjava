package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {

        if (!meal.isNew() && this.get(meal.getId(), userId) == null)
            return null;

        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);
        
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {

        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("userId", userId).setParameter("id", id).executeUpdate() != 0;

        //return em.createNamedQuery(Meal.DELETE).setParameter("id", id).executeUpdate() != 0;

        /*try {
            mealList.remove(id);
            return true;
        }
        catch (NotFoundException e)
        {
            return false;
        }*/
    }

    @Override
    public Meal get(int id, int userId) {

        List<Meal> mealList = em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("userId", userId).getResultList();

        return mealList.stream().filter(m -> m.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        //return null;

        List<Meal> mealList = em.createNamedQuery(Meal.FIND_BETWEEN_DATES, Meal.class).setParameter("userId", userId).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();

        return mealList;
    }
}