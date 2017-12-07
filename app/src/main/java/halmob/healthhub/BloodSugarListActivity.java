package halmob.healthhub;

/**
 * Created by hakan on 19.11.2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import halmob.healthhub.EventListeners.BloodSugarListener;
import halmob.healthhub.Models.BloodSugar;


public class BloodSugarListActivity extends AppCompatActivity implements BloodSugarListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar_list);
        Intent myIntent = getIntent(); // gets the previously created intent
        String userId = myIntent.getStringExtra("userId");
        if(userId == null){
            userId=FirebaseUtil.getCurrentUserId();
        }
        FirebaseTransaction.setmBloodSugarListener(this);
        FirebaseTransaction.getBloodSugars(userId);
    }

    @Override
    public void bloodSugarsRead(List<BloodSugar> bloodSugarList){
        ListAdapter bloodSugarListAdapter = new CustomBloodSugarAdapter(this, bloodSugarList);
        ListView listViewBloodSugar = (ListView) findViewById(R.id.listViewMedicine);
        listViewBloodSugar.setAdapter(bloodSugarListAdapter);
    }
}
