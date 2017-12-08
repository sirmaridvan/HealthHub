package halmob.healthhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import halmob.healthhub.EventListeners.ProgramForBodyWorkListener;
import halmob.healthhub.EventListeners.ProgramForCardioListener;
import halmob.healthhub.Models.SportProgramForBodyWork;
import halmob.healthhub.Models.SportProgramForCardio;

public class ListSportProgramActivity extends AppCompatActivity implements ProgramForBodyWorkListener,ProgramForCardioListener{
    private Button listCardioRecordButton;
    private Button listBodyWorkRecordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sport_program);
        listCardioRecordButton = findViewById(R.id.ListCardioRecord);
        listCardioRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_list_cardio_sport_program);
                ListCardioOnCreate();

            }
        });

        listBodyWorkRecordButton = findViewById(R.id.ListBodyWorkRecord);
        listBodyWorkRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_list_bodywork_sport_program);
                ListBodyWorkOnCreate();

            }
        });
    }
    protected void ListBodyWorkOnCreate() {
        setContentView(R.layout.activity_list_bodywork_sport_program);
        Intent myIntent = getIntent(); // gets the previously created intent
        String userId = myIntent.getStringExtra("userId");
        if(userId == null){
            userId=FirebaseUtil.getCurrentUserId();
        }

        FirebaseTransaction.setProgramForBodyWorkListenerListener(this);
        FirebaseTransaction.getBodyWorkProgram(userId);

        listCardioRecordButton = findViewById(R.id.ListCardioRecord);
        listCardioRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_list_cardio_sport_program);
                ListCardioOnCreate();

            }
        });

        listBodyWorkRecordButton = findViewById(R.id.ListBodyWorkRecord);
        listBodyWorkRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_list_bodywork_sport_program);
                ListBodyWorkOnCreate();

            }
        });
    }

    public void BodyWorkProgramRead(List<SportProgramForBodyWork> BodyWorkList){
        ListAdapter bodyWorkListAdapter = new CustomProgramBodyWorkAdapter(this, BodyWorkList);
        ListView listBodyWorkRecord = (ListView) findViewById(R.id.listBodyWorkRecord);
        listBodyWorkRecord.setAdapter(bodyWorkListAdapter);

    }

    protected void ListCardioOnCreate() {
        setContentView(R.layout.activity_list_cardio_sport_program);

        FirebaseTransaction.setProgramForCardioListenerListener(this);
        Intent myIntent = getIntent(); // gets the previously created intent
        String userId = myIntent.getStringExtra("userId");
        if(userId == null){
            userId=FirebaseUtil.getCurrentUserId();
        }
        FirebaseTransaction.getCardioProgram(userId);

        listCardioRecordButton = findViewById(R.id.ListCardioRecord);
        listCardioRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_list_cardio_sport_program);
                ListCardioOnCreate();

            }
        });

        listBodyWorkRecordButton = findViewById(R.id.ListBodyWorkRecord);
        listBodyWorkRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_list_bodywork_sport_program);
                ListBodyWorkOnCreate();

            }
        });

    }
    @Override
    public void CardioProgramRead(List<SportProgramForCardio> CardioList){
        ListAdapter cardioListAdapter = new CustomProgramCardioAdapter(this, CardioList);
        ListView listCardioRecord = (ListView) findViewById(R.id.listCardioRecord);
        listCardioRecord.setAdapter(cardioListAdapter);
    }

}



