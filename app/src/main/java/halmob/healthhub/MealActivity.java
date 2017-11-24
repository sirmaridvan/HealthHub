package halmob.healthhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import halmob.healthhub.EventListeners.FoodListener;
import halmob.healthhub.Models.Food;
import halmob.healthhub.Models.Meal;

public class MealActivity extends AppCompatActivity implements FoodListener {
    private Spinner foodTypeSpinner;
    private Spinner fruitFoodNameSpinner;
    private Spinner milkProductFoodNameSpinner;
    private Spinner vegetableFoodNameSpinner;
    private Spinner dessertFoodNameSpinner;
    private Spinner meatFoodNameSpinner;
    private Spinner riceFoodNameSpinner;
    private Spinner pastaFoodNameSpinner;
    private String selectedFoodType;
    private String selectedFood;
    private TextView foodNameTextView;
    private boolean spinnerInitFlag;
    private ArrayList<Food> allFoodList = new ArrayList<>();;
    private EditText portionSizeEditText;
    private Button mealSubmitButton;
    Meal NewMeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        FirebaseTransaction.setFoodListener(this);
        FirebaseTransaction.getFoods();
        spinnerInitFlag = false;

        portionSizeEditText = (EditText) findViewById(R.id.portion_size_input_edit_text);

        //foodType spinner creation code
        Spinner spinner = (Spinner) findViewById(R.id.food_type_spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.food_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        foodTypeSpinner = (Spinner) findViewById(R.id.food_type_spinner);

        foodNameTextView = (TextView) findViewById(R.id.food_name_text_view);

        foodTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedFoodType = String.valueOf(foodTypeSpinner.getSelectedItem());

                if(selectedFoodType.equals("Select Food Type")) {
                    foodNameTextView.setVisibility(View.INVISIBLE);
                    if(spinnerInitFlag == true) { //if the program applied the steps for the first time
                        milkProductFoodNameSpinner.setVisibility(View.INVISIBLE);
                        fruitFoodNameSpinner.setVisibility(View.INVISIBLE);
                    }
                }
                else if( selectedFoodType.equals("Milk Product")) {
                    foodNameTextView.setVisibility(View.VISIBLE);

                    //String t = allFoodList.get(1).getFoodName();
                    Meal m = new Meal(allFoodList.get(1),"1.5");
                    //String s = fruitFoodNameSpinner.getSelectedItem().toString();
                    milkProductFoodNameSpinner.setVisibility(View.VISIBLE);
                    fruitFoodNameSpinner.setVisibility(View.INVISIBLE);
                }
                else if( selectedFoodType.equals("Fruit")) {
                    foodNameTextView.setVisibility(View.VISIBLE);

                    fruitFoodNameSpinner.setVisibility(View.VISIBLE);
                    milkProductFoodNameSpinner.setVisibility(View.INVISIBLE);
                }

                else if( selectedFoodType.equals("Vegetable")) {
                    foodNameTextView.setVisibility(View.VISIBLE);

                    fruitFoodNameSpinner.setVisibility(View.VISIBLE);
                }

                else if( selectedFoodType.equals("Dessert")) {
                    foodNameTextView.setVisibility(View.VISIBLE);

                    fruitFoodNameSpinner.setVisibility(View.VISIBLE);
                }

                else if( selectedFoodType.equals("Meat and Meat Products")) {
                    foodNameTextView.setVisibility(View.VISIBLE);

                    fruitFoodNameSpinner.setVisibility(View.VISIBLE);
                }

                else if( selectedFoodType.equals("Rice")) {
                    foodNameTextView.setVisibility(View.VISIBLE);

                    fruitFoodNameSpinner.setVisibility(View.VISIBLE);
                }

                else if( selectedFoodType.equals("Pasta")) {
                    foodNameTextView.setVisibility(View.VISIBLE);

                    fruitFoodNameSpinner.setVisibility(View.VISIBLE);
                }

                spinnerInitFlag = true;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });




        mealSubmitButton = (Button) findViewById(R.id.meal_submit_button);
        mealSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitMeal();
                FirebaseTransaction.addMeal(NewMeal);
                Toast.makeText(getApplicationContext(),
                        "Blood Sugar Record is saved successfully!",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    public void foodRead(List<Food> foods){


        //fruitFoodName spinner creation code
        Spinner spinner2 = (Spinner) findViewById(R.id.fruit_food_name_spinner);

        List<String> fruitFoodNames = new ArrayList<String>();

        for (int i=0;i<foods.size();i++) {
            if(foods.get(i) != null )
                allFoodList.add(foods.get(i));

            if(foods.get(i).getFoodType().equals("Fruit") )
                fruitFoodNames.add(foods.get(i).getFoodName());
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fruitFoodNames);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        fruitFoodNameSpinner = (Spinner) findViewById(R.id.fruit_food_name_spinner);


        //milkProductFoodName spinner creation code
        Spinner spinner3 = (Spinner) findViewById(R.id.milk_food_name_spinner);

        List<String> milkProductFoodNames = new ArrayList<String>();

        for (int i=0;i<foods.size();i++) {
            if(foods.get(i).getFoodType().equals("Milk Product") )
                milkProductFoodNames.add(foods.get(i).getFoodName());
        }
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, milkProductFoodNames);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        milkProductFoodNameSpinner = (Spinner) findViewById(R.id.milk_food_name_spinner);


        /*
        DAHA SONRA EKLEYECEGİM SPİNNERLAR


        //vegetableFoodName spinner creation code
        Spinner spinner4 = (Spinner) findViewById(R.id.fruit_food_name_spinner);

        List<String> VegetableFoodNames = new ArrayList<String>();

        for (int i=0;i<foods.size();i++) {
            if(foods.get(i).getFoodType().equals("Vegetable") )
                VegetableFoodNames.add(foods.get(i).getFoodName());
        }
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, VegetableFoodNames);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        vegetableFoodNameSpinner = (Spinner) findViewById(R.id.fruit_food_name_spinner);



        //dessertFoodName spinner creation code
        Spinner spinner5 = (Spinner) findViewById(R.id.fruit_food_name_spinner);

        List<String> dessertFoodNames = new ArrayList<String>();

        for (int i=0;i<foods.size();i++) {
            if(foods.get(i).getFoodType().equals("Dessert") )
                dessertFoodNames.add(foods.get(i).getFoodName());
        }
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dessertFoodNames);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        dessertFoodNameSpinner = (Spinner) findViewById(R.id.fruit_food_name_spinner);

        //aşağıdaki şekilde kullanılabilir, denemek için commentı kaldırıp debugger kullan
        Food newFood = foods.get(1);
        //String calorie = newFood.getCalorie();
        //int a=1;

        */
    }

    public void submitMeal()
    {
        if( selectedFoodType.equals("Milk Product" ) ) {
            selectedFood = String.valueOf(milkProductFoodNameSpinner.getSelectedItem());
        }

        else if( selectedFoodType.equals("Fruit" ) ) {
            selectedFood = String.valueOf(fruitFoodNameSpinner.getSelectedItem());
        }



        String stringPortionSize = portionSizeEditText.getText().toString();
        //String stringBloodSugarValue = editTextSugarValue.getText().toString();
        //int intBloodSugarValue = -1;

//        try {
  //          intBloodSugarValue = Integer.parseInt(stringBloodSugarValue);
      //  }
    //    catch (NumberFormatException e) {

        //}



        createMealRecord(stringPortionSize);

    }

    public void createMealRecord(String stringNewPortionSize) {
        Food newFoodRecord = null;
        for( int i = 0; i < allFoodList.size(); i++ ) {
            if(selectedFood.equals(allFoodList.get(i).getFoodName())) {
                newFoodRecord = allFoodList.get(i);
            }
        }


        if( newFoodRecord != null )
            NewMeal = new Meal(newFoodRecord, stringNewPortionSize );
            NewMeal.setDate();
            NewMeal.setTime();
    }
}
