package halmob.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MedicalAnalysisActivity extends AppCompatActivity {
    private Button addButton;
    private Button showButton;
    private ImageButton addButton1;
    private ImageButton showButton1;
    private LinearLayout addLayout;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_analysis);
        Intent intent = getIntent(); // gets the previously created intent
        userId = intent.getStringExtra("userId");


        addLayout = findViewById(R.id.addLayout1);

        // supervisor ise add button görünmez olur
        if(FirebaseUtil.userType.equals("Supervisor")){
            addLayout.setVisibility(View.GONE);
            // kaybolmuyorsa addButton ve addButton1'i ayrı ayrı GONE yap.
        }

        addButton = findViewById(R.id.addAnalysis_2);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalAnalysisActivity.this, AddAnalysisActivity.class);
                startActivity(intent);
            }
        });

        addButton1 = findViewById(R.id.addAnalysis_1);
        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalAnalysisActivity.this, AddAnalysisActivity.class);
                startActivity(intent);
            }
        });

        showButton = findViewById(R.id.showAnalysis_2);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalAnalysisActivity.this, MedicalAnalysisReportsActivity.class);
                startActivity(intent);
            }
        });

        showButton1 = findViewById(R.id.showAnalysis_1);
        showButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalAnalysisActivity.this, MedicalAnalysisReportsActivity.class);
                startActivity(intent);
            }
        });
    }
}
