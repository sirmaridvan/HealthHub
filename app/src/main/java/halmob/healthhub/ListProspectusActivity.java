package halmob.healthhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import halmob.healthhub.EventListeners.ProspectusListener;
import halmob.healthhub.Models.ProspectusInfo;

public class ListProspectusActivity extends AppCompatActivity implements ProspectusListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prospectus);

        FirebaseTransaction.setProspectusListenerListener(this);
        FirebaseTransaction.getProspectuses();
    }

    @Override
    public void prospectusRead(List<ProspectusInfo> prospectusList) {
        ListAdapter prospectusListAdapter = new CustomProspectusAdapter(this, prospectusList);
        ListView listViewProspectus = (ListView) findViewById(R.id.listViewProspectus);
        listViewProspectus.setAdapter(prospectusListAdapter);


    }
}
