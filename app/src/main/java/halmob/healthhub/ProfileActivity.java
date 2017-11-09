package halmob.healthhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import halmob.healthhub.Models.Drug;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {
    private Button followButton;
    private Button addDrugButton;
    private Button stepCounter;
    private DatabaseReference mPersonRef;
    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mPersonRef = FirebaseUtil.getPeopleRef();
        followButton = findViewById(R.id.follow);
        followButton.setOnClickListener(this);
        addDrugButton = findViewById(R.id.add_drug);
        addDrugButton.setOnClickListener(this);
        stepCounter = findViewById(R.id.step_counter);
        stepCounter.setOnClickListener(this);
        mUserId = "kuLAa0XboKVSwwiaP9PK80AaJGf1";
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.follow:
                follow();
                break;
            case R.id.add_drug:
                addDrug();
                break;
            case R.id.step_counter:
                Intent intent = new Intent(this, StepCounterActivity.class);
                startActivity(intent);
        }
    }
    public void follow(){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        if (currentUserId == null) {
            Toast.makeText(ProfileActivity.this, "You need to sign in to follow someone.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO: Convert these to actually not be single value, for live updating when
        // current user follows.
        mPersonRef.child(currentUserId).child("following").child(mUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> updatedUserData = new HashMap<>();
                updatedUserData.put("people/" + currentUserId + "/following/" + mUserId, true);
                updatedUserData.put("followers/" + mUserId + "/" + currentUserId, true);
                FirebaseUtil.getBaseRef().updateChildren(updatedUserData, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                        if (firebaseError != null) {
                            Toast.makeText(ProfileActivity.this, R.string.follow_user_error, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }
    public void addDrug(){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        Drug drug = new Drug();
        drug.setName("aspirin");
        drug.setHowMany("40");
        try {
            String date = "2018-11-12";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            drug.setEndTime(timestamp.toString());
        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }
        mPersonRef.child(currentUserId).child("drugs").push().setValue(drug, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference firebase) {
                if (error != null) {
                    Toast.makeText(ProfileActivity.this, "Error adding drug.", Toast
                            .LENGTH_SHORT).show();
                }
            }
        });

    }
}

