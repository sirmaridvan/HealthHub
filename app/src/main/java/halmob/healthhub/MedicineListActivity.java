package halmob.healthhub;

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
        FirebaseTransaction.getDrugs();
    }

    @Override
    public void drugsRead(List<Drug> drugList){
        ListAdapter medListAdapter = new CustomMedAdapter(this, drugList);
        ListView listViewMedicine = (ListView) findViewById(R.id.listViewMedicine);
        listViewMedicine.setAdapter(medListAdapter);
    }
}
