package halmob.healthhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addNewSportRecordActivity extends AppCompatActivity {
    private Button cardioButton;
    private Button bodyWorkButton;

    protected void OnCreateBodyWork(){
        setContentView(R.layout.activity_new_bodywork_record);

        cardioButton = findViewById(R.id.Cardio);
        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_new_cardio_record);
                OnCreateCardio();
            }
        });
    }

    protected void OnCreateCardio(){
        setContentView(R.layout.activity_new_cardio_record);

        cardioButton = findViewById(R.id.Cardio);
        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_new_cardio_record);
                OnCreateCardio();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sport_record);

        cardioButton = findViewById(R.id.Cardio);
        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_new_cardio_record);
            }
        });

        bodyWorkButton = findViewById(R.id.BodyWork);
        bodyWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_new_bodywork_record);
                OnCreateBodyWork();
            }
        });


    }
}
