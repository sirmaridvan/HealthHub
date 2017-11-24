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

        bloodSugarTableButton = findViewById(R.id.blood_sugar_table_button);
        bloodSugarTableButton.setOnClickListener(new View.OnClickListener() {
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
                Intent i = new Intent(DiabTableActivity.this, BloodSugarListActivity.class);
                startActivity(i);
            }
        });
    }




}
