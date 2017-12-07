package halmob.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import halmob.healthhub.EventListeners.ReportListener;
import halmob.healthhub.Models.Report;

public class ReportActivity extends AppCompatActivity implements ReportListener {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Intent myIntent = getIntent(); // gets the previously created intent
        String userId = myIntent.getStringExtra("userId");
        if(userId == null){
            userId=FirebaseUtil.getCurrentUserId();
        }



        FirebaseTransaction.setReportListenerListener(this);
        FirebaseTransaction.getReports(userId);



    }

    @Override
    public void reportRead(List<Report> reportList){
        int sizeOfList=reportList.size();
        final String[] uri = new String[sizeOfList];
        for(int i=0;i<sizeOfList;i++){
            uri[i]=reportList.get(i).getReportUri();
        }
        ReportList adapter = new
                ReportList(ReportActivity.this,getApplicationContext(), uri);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ReportActivity.this, "You Clicked at " +uri[+ position], Toast.LENGTH_SHORT).show();

            }
        });
    }
}
