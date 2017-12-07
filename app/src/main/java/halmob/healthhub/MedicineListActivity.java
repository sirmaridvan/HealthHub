package halmob.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import halmob.healthhub.EventListeners.DrugListener;
import halmob.healthhub.Models.Drug;

public class MedicineListActivity extends AppCompatActivity implements DrugListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);

        FirebaseTransaction.setDrugListenerListener(this);
        Intent myIntent = getIntent(); // gets the previously created intent
        String userId = myIntent.getStringExtra("userId");
        if(userId == null){
            userId=FirebaseUtil.getCurrentUserId();
        }
        FirebaseTransaction.getDrugs(userId);
    }

    @Override
    public void drugsRead(List<Drug> drugList){
        ListAdapter medListAdapter = new CustomMedAdapter(this, drugList);
        ListView listViewMedicine = (ListView) findViewById(R.id.listViewMedicine);
        listViewMedicine.setAdapter(medListAdapter);
    }
}
