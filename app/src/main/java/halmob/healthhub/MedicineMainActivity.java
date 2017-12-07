package halmob.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MedicineMainActivity extends AppCompatActivity {
    private Button addButton;
    private Button listButton;
    private Button addProspectusButton;
    private Button listProspectusButton;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_main);
        Intent intent = getIntent(); // gets the previously created intent
        userId = intent.getStringExtra("userId");


        // supervisor ise add button görünmez olur
        if(FirebaseUtil.userType.equals("Supervisor")){
            addButton.setVisibility(View.GONE);
            addProspectusButton.setVisibility(View.GONE);
        }

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicineMainActivity.this, MedicineActivity.class);
                startActivity(intent);
            }
        });

        listButton = findViewById(R.id.listButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicineMainActivity.this, MedicineListActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        // add prospectus activity
        addProspectusButton = findViewById(R.id.prospectusAddButton);
        addProspectusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MedicineMainActivity.this, AddProspectusActivity.class);
                startActivity(intent);
            }
        });

        // list prospectus activity
        listProspectusButton = findViewById(R.id.prospectusInfoButton);
        listProspectusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MedicineMainActivity.this, ListProspectusActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });


    }
}
