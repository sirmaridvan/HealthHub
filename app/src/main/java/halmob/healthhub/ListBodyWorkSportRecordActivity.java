package halmob.healthhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import halmob.healthhub.EventListeners.BodyWorkListener;
import halmob.healthhub.Models.SportForBodyWork;

public class ListBodyWorkSportRecordActivity extends AppCompatActivity implements BodyWorkListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodywork_record_list);

        FirebaseTransaction.setBodyWorkListenerListener(this);
        FirebaseTransaction.getBodyWork();
    }
    @Override
    public void BodyWorkRead(List<SportForBodyWork> BodyWorkList){
        ListAdapter bodyWorkListAdapter = new CustomBodyWorkRecordAdapter(this, BodyWorkList);
        ListView listBodyWorkRecord = (ListView) findViewById(R.id.listBodyWorkRecord);
        listBodyWorkRecord.setAdapter(bodyWorkListAdapter);
    }
}

