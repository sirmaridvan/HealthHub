package halmob.healthhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import halmob.healthhub.EventListeners.InsulinDoseListener;
import halmob.healthhub.Models.InsulinDose;

/**
 * Created by hakan on 22.11.2017.
 */

public class InsulinDoseListActivity extends AppCompatActivity implements InsulinDoseListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insulin_dose_list);

        FirebaseTransaction.setmInsulinDoseListener(this);
        FirebaseTransaction.getInsulinDoses();
    }

    @Override
    public void insulinDosesRead(List<InsulinDose> insulinDoseList){
        ListAdapter insulinDoseListAdapter = new CustomInsulinDoseAdapter(this, insulinDoseList);
        ListView listViewInsulinDose = (ListView) findViewById(R.id.listViewInsulinDoses);
        listViewInsulinDose.setAdapter(insulinDoseListAdapter);
    }
}
