package halmob.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MedicalAnalysisActivity extends AppCompatActivity {
    private Button addButton;
    private Button showButton;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_analysis);
        Intent intent = getIntent(); // gets the previously created intent
        userId = intent.getStringExtra("userId");
        FirebaseTransaction.follow(userId);

        addButton = findViewById(R.id.addAnalysis);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalAnalysisActivity.this, AddAnalysisActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        showButton = findViewById(R.id.showAnalysis);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalAnalysisActivity.this, MedicalAnalysisReportsActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
    }
}
