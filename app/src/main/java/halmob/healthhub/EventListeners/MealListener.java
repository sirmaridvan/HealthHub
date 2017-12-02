package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.Meal;

/**
 * Created by hakan on 2.12.2017.
 */

public interface MealListener {
    public void mealsRead(List<Meal> mealList);
}
