package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.getWithExceeded;

/**
 * Created by Alex on 30.03.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");

        //List<MealWithExceed> mealsList = MealsUtil.createMealsExceedStaticList();
        List<MealWithExceed> mealsList = getWithExceeded(MealsUtil.meals, 2000);

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("mealsList", mealsList);
        request.setAttribute("dateTimeFormat", MealsUtil.dateTimeFormatter);

        RequestDispatcher rd = request.getRequestDispatcher("meals.jsp");
        rd.forward(request, response);

        response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        /*MealsUtil.createMealsStaticList();
        List<MealWithExceed> mealsList = MealsUtil.mealsStaticList;

        req.setCharacterEncoding("UTF-8");
        req.setAttribute("mealsList", mealsList);

        RequestDispatcher rd = req.getRequestDispatcher("meals.jsp");
        rd.forward(req, resp);*/
    }
}
