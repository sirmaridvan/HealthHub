package halmob.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import halmob.healthhub.Models.Drug;

public class MainAcitivity extends BaseActivity implements View.OnClickListener {
    private Button followButton;
    private Button stepCounter;
    private Button addMedicineButton;
    private Button diabetesButton;
    private Button sportsPageButton;
    private Button profilePageButton;
    private Button logoutButton;
    private DatabaseReference mPersonRef;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mPersonRef = FirebaseUtil.getPeopleRef();
        followButton = findViewById(R.id.follow);
        followButton.setOnClickListener(this);
        stepCounter = findViewById(R.id.step_counter);
        stepCounter.setOnClickListener(this);
        addMedicineButton = findViewById(R.id.add_medicine);
        addMedicineButton.setOnClickListener(this);
        diabetesButton = findViewById(R.id.diabetes_button);
        diabetesButton.setOnClickListener(this);
        sportsPageButton = findViewById(R.id.sportsPage_button);
        sportsPageButton.setOnClickListener(this);
        profilePageButton = findViewById(R.id.profilePage_button);
        profilePageButton.setOnClickListener(this);
        logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.follow:
                FirebaseTransaction.follow("GldLQw9wyiV2wvfDo6OiMi2QhFa2");
                break;
            case R.id.step_counter:
                intent = new Intent(this, StepCounterActivity.class);
                startActivity(intent);
                break;
            case R.id.add_medicine:
                intent = new Intent(this, MedicineActivity.class);
                startActivity(intent);
                break;
            case R.id.diabetes_button:
                intent = new Intent(this, DiabActivity.class);
                startActivity(intent);
                break;
            case R.id.sportsPage_button:
                intent = new Intent(this, Sports_Activity.class);
                startActivity(intent);
                break;
            case R.id.profilePage_button:
                intent = new Intent(this, ProfilePageActivity.class);
                startActivity(intent);
                break;
            case R.id.logout_button:
                signOut();
                break;
        }
    }
    private void signOut() {
        // Firebase sign out
        FirebaseAuth.getInstance().signOut();

        /*// Google sign out
        Auth.GoogleSignInApi.signOut(LoginActivity.mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        finish();
                    }
                });*/
        finish();
        System.exit(0);
    }



}

