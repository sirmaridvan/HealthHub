package halmob.healthhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Sports_Activity extends AppCompatActivity {
    private Button addSportRecordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_);

        addSportRecordButton = findViewById(R.id.addSportRecord);
        addSportRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Sports_Activity.this, addNewSportRecordActivity.class);
                startActivity(i);
            }
        });
    }
}
