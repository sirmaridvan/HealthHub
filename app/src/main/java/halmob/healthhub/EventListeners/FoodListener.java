package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.Drug;
import halmob.healthhub.Models.Food;

/**
 * Created by RIDVAN SIRMA on 11/21/2017.
 */

public interface FoodListener {
    public void foodRead(List<Food> foodList);
}
