package halmob.healthhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DiabTableActivity extends AppCompatActivity {
    private ImageButton insulinTableButton;
    private ImageButton bloodSugarTableButton;
    private ImageButton mealTableButton;
    private ImageButton carbTableButton;
    private Button insulinTableButton2;
    private Button bloodSugarTableButton2;
    private Button mealTableButton2;
    private Button carbTableButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diab_table);


        insulinTableButton = findViewById(R.id.insulin_table_button);
        insulinTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiabTableActivity.this, InsulinDoseListActivity.class);
                startActivity(i);
            }
        });

        insulinTableButton2 = findViewById(R.id.insulin_table_button_2);
        insulinTableButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiabTableActivity.this, InsulinDoseListActivity.class);
                startActivity(i);
            }
        });

        bloodSugarTableButton = findViewById(R.id.blood_sugar_table_button);
        bloodSugarTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiabTableActivity.this, BloodSugarListActivity.class);
                startActivity(i);
            }
        });

        bloodSugarTableButton2 = findViewById(R.id.blood_sugar_table_button_2);
        bloodSugarTableButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiabTableActivity.this, BloodSugarListActivity.class);
                startActivity(i);
            }
        });

        mealTableButton = findViewById(R.id.meal_table_button);
        mealTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiabTableActivity.this, MealListActivity.class);
                startActivity(i);
            }
        });

        mealTableButton2 = findViewById(R.id.meal_table_button_2);
        mealTableButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiabTableActivity.this, MealListActivity.class);
                startActivity(i);
            }
        });

        carbTableButton = findViewById(R.id.carb_count_table_button);
        carbTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiabTableActivity.this, CarbCountListActivity.class);
                startActivity(i);
            }
        });

        carbTableButton2 = findViewById(R.id.carb_count_table_button_2);
        carbTableButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiabTableActivity.this, CarbCountListActivity.class);
                startActivity(i);
            }
        });
    }




}
