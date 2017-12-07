package halmob.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import halmob.healthhub.EventListeners.MealListener;
import halmob.healthhub.Models.Meal;

/**
 * Created by hakan on 2.12.2017.
 */

public class MealListActivity extends AppCompatActivity implements MealListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);
        Intent myIntent = getIntent(); // gets the previously created intent
        String userId = myIntent.getStringExtra("userId");
        if(userId == null){
            userId=FirebaseUtil.getCurrentUserId();
        }

        FirebaseTransaction.setmMealListener(this);
        FirebaseTransaction.getMeals(userId);
    }

    @Override
    public void mealsRead(List<Meal> mealList){
        ListAdapter mealListAdapter = new CustomMealAdapter(this, mealList);
        ListView listViewMeals = (ListView) findViewById(R.id.listViewMeals);
        listViewMeals.setAdapter(mealListAdapter);
    }
}
