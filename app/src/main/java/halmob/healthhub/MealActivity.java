package halmob.healthhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import halmob.healthhub.EventListeners.FoodListener;
import halmob.healthhub.Models.Food;

public class MealActivity extends AppCompatActivity implements FoodListener {
    private Spinner foodTypeSpinner;
    private Spinner foodNameSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        FirebaseTransaction.setFoodListener(this);
        FirebaseTransaction.getFoods();
        //selectedFoodTypeList = new ArrayList<Food>();
        //Food selectedFood = selectedFoodTypeList.get(1);
    }

    @Override
    public void foodRead(List<Food> foods){
        //foodType spinner creation code
        Spinner spinner = (Spinner) findViewById(R.id.food_type_spinner);

        foods.size();
        List<String> foodTypes = new ArrayList<String>();
        foodTypes.add(foods.get(0).getFoodType());
        foodTypes.add(foods.get(1).getFoodType());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, foodTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        foodTypeSpinner = (Spinner) findViewById(R.id.food_type_spinner);



        //foodName spinner creation code
        Spinner spinner2 = (Spinner) findViewById(R.id.food_name_spinner);

        foods.size();
        List<String> foodNames = new ArrayList<String>();
        foodNames.add(foods.get(0).getFoodName());
        foodNames.add(foods.get(1).getFoodName());

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, foodNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        foodNameSpinner = (Spinner) findViewById(R.id.food_name_spinner);

        //aşağıdaki şekilde kullanılabilir, denemek için commentı kaldırıp debugger kullan
        Food newFood = foods.get(1);
        //String calorie = newFood.getCalorie();
        //int a=1;
    }
}
