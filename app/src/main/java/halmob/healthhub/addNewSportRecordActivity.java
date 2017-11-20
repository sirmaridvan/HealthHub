package halmob.healthhub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import halmob.healthhub.Models.SportForBodyWork;
import halmob.healthhub.Models.SportForCardio;

public class addNewSportRecordActivity extends AppCompatActivity {
    private Button cardioButton;
    private Button bodyWorkButton;
    private Button submitForBodyWork;
    private Button submitForCardio;

    private DatePickerDialog.OnDateSetListener dateSetForBodyWork;
    private DatePickerDialog.OnDateSetListener dateSetForCardio;

    private EditText editExerciseNameForBodywork;
    private EditText editNumberOfSetsForBodywork;
    private EditText editNumberOfRepetitionForBodyWork;
    private EditText editWeightForBodyWork;
    private TextView editExerciseDateForBodyWork;

    private EditText editExerciseNameForCardio;
    private EditText editMinuteOfExerciseForCardio;
    private EditText editBurnedCaloriesForCardio;
    private TextView editExerciseDateForCardio;


    SportForBodyWork BodyWork;
    SportForCardio Cardio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sport_record);

        cardioButton = findViewById(R.id.Cardio);
        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_new_cardio_record);
            }
        });

        bodyWorkButton = findViewById(R.id.BodyWork);
        bodyWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_new_bodywork_record);
                OnCreateBodyWork();
            }
        });


    }

//////////////////////////////////////////////////////////////////////////////////////////
    //FOR BODYWORK
//////////////////////////////////////////////////////////////////////////////////////
    protected void OnCreateBodyWork() {
    setContentView(R.layout.activity_new_bodywork_record);

    BodyWork = new SportForBodyWork();

    editExerciseNameForBodywork = (EditText) findViewById(R.id.InputBodyWorkExerciseName);
    editNumberOfSetsForBodywork = (EditText) findViewById(R.id.InputBodyWorkNumberOfSet);
    editNumberOfRepetitionForBodyWork = (EditText) findViewById(R.id.InputBodyWorkNumberOfRepetition);
    editWeightForBodyWork = (EditText) findViewById(R.id.InputBodyWorkWeight);
    editExerciseDateForBodyWork = (TextView) findViewById(R.id.InputDateForBodyWorkRecord);

    editExerciseDateForBodyWork.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Calendar cal1 = Calendar.getInstance();
            int year = cal1.get(Calendar.YEAR);
            int month = cal1.get(Calendar.MONTH);
            int day = cal1.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog1 = new DatePickerDialog(addNewSportRecordActivity.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    dateSetForBodyWork,
                    year, month, day
            );
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog1.show();

        }
    });

    dateSetForBodyWork = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            month = month + 1;  // since index starts from 0
            String date1 = year + "-" + month + "-" + day;
            editExerciseDateForBodyWork.setText(date1);
        }
    };

    submitForBodyWork = (Button) findViewById(R.id.button_submitForBodyWorkRecord);
    submitForBodyWork.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            submitToBodyWork();
            //FirebaseTransaction.addDrug(BodyWork);

            Toast.makeText(getApplicationContext(),
                    "Sport Record is saved successfully!",
                    Toast.LENGTH_LONG).show();

            Intent intent1 = new Intent(addNewSportRecordActivity.this, Sports_Activity.class);
            startActivity(intent1);
            finish();

        }
    });

    cardioButton = findViewById(R.id.Cardio);
    cardioButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.activity_new_cardio_record);
            OnCreateCardio();
        }
    });
}
    public void BodyWorkRead(List<SportForBodyWork> BodyWorkList){
    //kullanıcıyla ilgili bütün ilaçlar drugList'in içinde
    //istediğin gibi kullan
    }
    public void submitToBodyWork() {
        String input1 = editExerciseNameForBodywork.getText().toString();
        String input2 = editNumberOfSetsForBodywork.getText().toString();
        String input3 = editNumberOfRepetitionForBodyWork.getText().toString();
        String input4 = editWeightForBodyWork.getText().toString();
        String input5 = editExerciseDateForBodyWork.getText().toString();

        createBodyWorkExercise(input1, input2, input3, input4, input5);

    }
    public void createBodyWorkExercise(String s1, String s2, String s3, String s4, String s5) {
        BodyWork.setNameOfExerciseForBodyWork(s1);
        BodyWork.setNumberOfSetForBodyWork(s2);
        BodyWork.setNumberOfRepetitionForBodyWork(s3);
        BodyWork.setWeightForBodyWork(s4);
        BodyWork.setExerciseDateForBodyWork(s5);
    }
//////////////////////////////////////////////////////////////////////////////
    //FOR CARDIO
//////////////////////////////////////////////////////////////////////////////
    protected void OnCreateCardio() {
    setContentView(R.layout.activity_new_cardio_record);

    Cardio = new SportForCardio();

    editExerciseNameForCardio = (EditText) findViewById(R.id.InputCardioExerciseName);
    editMinuteOfExerciseForCardio = (EditText) findViewById(R.id.InputCardioMinute);
    editBurnedCaloriesForCardio = (EditText) findViewById(R.id.InputCardioBurnedCalories);
    editExerciseDateForCardio = (TextView) findViewById(R.id.InputDateForCardiRecordRecord);

    editExerciseDateForCardio.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Calendar cal1 = Calendar.getInstance();
            int year = cal1.get(Calendar.YEAR);
            int month = cal1.get(Calendar.MONTH);
            int day = cal1.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog1 = new DatePickerDialog(addNewSportRecordActivity.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    dateSetForCardio,
                    year, month, day
            );
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog1.show();

        }
    });

    submitForCardio = (Button) findViewById(R.id.button_submitForCardioRecord);
    submitForCardio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            submitToCardio();
            //FirebaseTransaction.addDrug(BodyWork);

            Toast.makeText(getApplicationContext(),
                    "Sport Record is saved successfully!",
                    Toast.LENGTH_LONG).show();

            Intent intent1 = new Intent(addNewSportRecordActivity.this, Sports_Activity.class);
            startActivity(intent1);
            finish();

        }
    });

    bodyWorkButton = findViewById(R.id.BodyWork);
    bodyWorkButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.activity_new_bodywork_record);
            OnCreateCardio();
        }
    });
}
    public void CardioRead(List<SportForCardio> CardioList){

    }
    public void submitToCardio() {
        String input1 = editExerciseNameForCardio.getText().toString();
        String input2 = editMinuteOfExerciseForCardio.getText().toString();
        String input3 = editBurnedCaloriesForCardio.getText().toString();
        String input4 = editExerciseDateForCardio.getText().toString();

        createCardioExercise(input1, input2, input3, input4);

    }
    public void createCardioExercise(String s1, String s2, String s3, String s4) {
        Cardio.setNameOfExerciseForCardio(s1);
        Cardio.setMinuteOfExerciseForCardio(s2);
        Cardio.setBurnedCaloriesForCardio(s3);
        Cardio.setExerciseDateForCardio(s4);
    }
    ////////////////////////////////////////////////////////
}

