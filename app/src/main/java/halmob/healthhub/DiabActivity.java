package halmob.healthhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DiabActivity extends AppCompatActivity {
    private ImageButton insulinButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diab);

        insulinButton = findViewById(R.id.insulin_button);
        insulinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiabActivity.this, InsulinActivity.class);
                startActivity(i);
            }
        });
    }



}
