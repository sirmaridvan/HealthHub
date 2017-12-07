package halmob.healthhub;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.roger.catloadinglibrary.CatLoadingView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button followButton;
    private Button stepCounter;
    private Button trackMedicineButton;
    private Button diabetesButton;
    private Button mealButton;
    private Button sportsPageButton;
    private Button profilePageButton;
    private Button logoutButton;
    private Button MedicalAnalysisButton;
    private Button HearthRateButton;
    private Button UserSearch;
    private Button waitAnim;
    private DatabaseReference mPersonRef;
    private Intent intent;
    private CatLoadingView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = new CatLoadingView();
        waitAnim = findViewById(R.id.wait_animation);
        waitAnim.setOnClickListener(this);
        mPersonRef = FirebaseUtil.getPeopleRef();
        followButton = findViewById(R.id.follow);
        followButton.setOnClickListener(this);
        stepCounter = findViewById(R.id.step_counter);
        stepCounter.setOnClickListener(this);
        trackMedicineButton = findViewById(R.id.track_medicine);
        trackMedicineButton.setOnClickListener(this);
        HearthRateButton = findViewById(R.id.heart_rate_button);
        HearthRateButton.setOnClickListener(this);
        diabetesButton = findViewById(R.id.diabetes_button);
        diabetesButton.setOnClickListener(this);
        mealButton = findViewById(R.id.meal_page_button);
        mealButton.setOnClickListener(this);
        sportsPageButton = findViewById(R.id.sportsPage_button);
        sportsPageButton.setOnClickListener(this);
        profilePageButton = findViewById(R.id.profilePage_button);
        profilePageButton.setOnClickListener(this);
        logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(this);
        MedicalAnalysisButton = findViewById(R.id.medical_analysis_page);
        MedicalAnalysisButton.setOnClickListener(this);
        UserSearch = findViewById(R.id.user_search_button);
        UserSearch.setOnClickListener(this);

        // camera permission
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 111);
        }


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
            case R.id.track_medicine:
                intent = new Intent(this, MedicineMainActivity.class);
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
                intent.putExtra("userId",FirebaseUtil.getCurrentUserId());
                startActivity(intent);
                break;
            case R.id.logout_button:
                signOut();
                break;
            case R.id.user_search_button:
                intent = new Intent(this, UserSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.medical_analysis_page:
                intent = new Intent(this, MedicalAnalysisActivity.class);
                startActivity(intent);
                break;
            case R.id.wait_animation:
                mView.show(getSupportFragmentManager(), "");
                // kapatmak için "mView.dismiss();"
                break;
            case R.id.heart_rate_button:
                intent = new Intent(this, HeartRateMonitorActivity.class);
                startActivity(intent);
                break;
            case R.id.meal_button:
                // intent'in içerisine yeni yapılacak activity konacak: show meal add meal içerecek
                intent = new Intent(this, MealActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void signOut() {
        // Firebase sign out
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
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

