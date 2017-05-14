package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 *
 */
@Controller
@RequestMapping("/meals")
public class JspMealController extends MealRestController {

    @Autowired
    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping
    public String getAllMeals(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("/delete/{id}")
    public String deleteMeal(@PathVariable("id") int id) {
        delete(id);
        return "redirect:/meals";
    }

    @GetMapping("/update/{id}")
    public String editMeal(@PathVariable("id") int id, Model model) {
        model.addAttribute("meal", get(id));
        return "meal";
    }

    @GetMapping("/create")
    public String createMeal(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000));
        return "meal";
    }

    @PostMapping("/filter")
    public String getFilteredMeal(HttpServletRequest req, Model model) {

        LocalDate startDate = DateTimeUtil.parseLocalDate(resetParam("startDate", req));
        LocalDate endDate = DateTimeUtil.parseLocalDate(resetParam("endDate", req));
        LocalTime startTime = DateTimeUtil.parseLocalTime(resetParam("startTime", req));
        LocalTime endTime = DateTimeUtil.parseLocalTime(resetParam("endTime", req));

        model.addAttribute("meals", getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

    @PostMapping
    public String saveMeal(HttpServletRequest req) {
        String id = req.getParameter("id");

        Meal meal;
        try {
            meal = new Meal(
                    id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(req.getParameter("dateTime")),
                    req.getParameter("description"),
                    Integer.valueOf(req.getParameter("calories"))
            );

            if (meal.isNew()) {
                create(meal);
            } else {
                update(meal, meal.getId());
            }
        } catch (Exception e) {
            LOG.error("Couldn't save meal - some of the parameters are invalid! {}", e.getMessage());
        }

        return "redirect:/meals";
    }

    private String resetParam(String param, HttpServletRequest req) {
        String value = req.getParameter(param);
        req.setAttribute(param, value);
        return value;
    }

}
