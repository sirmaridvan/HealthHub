package halmob.healthhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import halmob.healthhub.EventListeners.BodyWorkListener;
import halmob.healthhub.EventListeners.CardioListener;
import halmob.healthhub.Models.SportForBodyWork;
import halmob.healthhub.Models.SportForCardio;

public class ListSportRecordActivity extends AppCompatActivity implements BodyWorkListener,CardioListener {
    private Button listCardioRecordButton;
    private Button listBodyWorkRecordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sport_record);

        listCardioRecordButton = findViewById(R.id.ListCardioRecord);
        listCardioRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_list_cardio_sport_record);
                ListCardioOnCreate();

            }
        });

        listBodyWorkRecordButton = findViewById(R.id.ListBodyWorkRecord);
        listBodyWorkRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_bodywork_record_list);
                ListBodyWorkOnCreate();

            }
        });
    }

    protected void ListBodyWorkOnCreate() {
        setContentView(R.layout.activity_bodywork_record_list);

        FirebaseTransaction.setBodyWorkListenerListener(this);
        FirebaseTransaction.getBodyWork();

        listCardioRecordButton = findViewById(R.id.ListCardioRecord);
        listCardioRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_list_cardio_sport_record);
                ListCardioOnCreate();

            }
        });

        listBodyWorkRecordButton = findViewById(R.id.ListBodyWorkRecord);
        listBodyWorkRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_bodywork_record_list);
                ListBodyWorkOnCreate();

            }
        });
        }

    public void BodyWorkRead(List<SportForBodyWork> BodyWorkList){
        ListAdapter bodyWorkListAdapter = new CustomBodyWorkRecordAdapter(this, BodyWorkList);
        ListView listBodyWorkRecord = (ListView) findViewById(R.id.listBodyWorkRecord);
        listBodyWorkRecord.setAdapter(bodyWorkListAdapter);

    }

    protected void ListCardioOnCreate() {
        setContentView(R.layout.activity_list_cardio_sport_record);

        FirebaseTransaction.setCardioListenerListener(this);
        FirebaseTransaction.getCardio();

        listCardioRecordButton = findViewById(R.id.ListCardioRecord);
        listCardioRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_list_cardio_sport_record);
                ListCardioOnCreate();

            }
        });

        listBodyWorkRecordButton = findViewById(R.id.ListBodyWorkRecord);
        listBodyWorkRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_bodywork_record_list);
                ListBodyWorkOnCreate();

            }
        });

    }
    @Override
    public void CardioRead(List<SportForCardio> CardioList){
        ListAdapter cardioListAdapter = new CustomCardioRecordAdapter(this, CardioList);
        ListView listCardioRecord = (ListView) findViewById(R.id.listCardioRecord);
        listCardioRecord.setAdapter(cardioListAdapter);
    }

}
