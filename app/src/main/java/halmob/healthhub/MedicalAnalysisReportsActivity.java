package halmob.healthhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import halmob.healthhub.EventListeners.ReportListener;
import halmob.healthhub.Models.Report;

public class MedicalAnalysisReportsActivity extends AppCompatActivity implements ReportListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_analysis_reports);

        FirebaseTransaction.setReportListenerListener(this);
        FirebaseTransaction.getReports();
    }


    @Override
    public void reportRead(final List<Report> reportList) {
        ListAdapter analysisListAdapter = new CustomReportListAdapter(this, reportList);
        ListView listViewReports = (ListView) findViewById(R.id.listViewReports);
        listViewReports.setAdapter(analysisListAdapter);

        listViewReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MedicalAnalysisReportsActivity.this, "Notes: " + reportList.get(i).getReportNotes(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
