package halmob.healthhub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import halmob.healthhub.EventListeners.StaticBodyWorkListener;

import halmob.healthhub.Models.SportForBodyWork;
import halmob.healthhub.Models.SportForCardio;
import halmob.healthhub.Models.StaticExerciseForBodyWork;

public class addNewSportRecordActivity extends AppCompatActivity {


    private Button cardioButton;
    private Button bodyWorkButton;
    private Button submitForCardio;

    private DatePickerDialog.OnDateSetListener dateSetForCardio;

    private EditText editExerciseNameForCardio;
    private EditText editMinuteOfExerciseForCardio;
    private EditText editBurnedCaloriesForCardio;
    private TextView editExerciseDateForCardio;

    private TextView LabelForMuscleRegion;
    private TextView LabelForBodyWorkTypeExercise;
    private TextView LabelForBodyWorkNumberOfSet;
    private TextView LabelForBodyWorkNumberOfRepetition;
    private TextView LabelForDateOfBodyWork;
    private TextView InputDateForBodyWorkRecord;
    private Button submitButtonForBodyWorkRecord;
    private EditText InputBodyWorkNumberOfSet;
    private EditText InputBodyWorkNumberOfRepetition;

    private boolean spinnerInitFlag;
    private Spinner MuscleRegionSpinner;
    private Spinner Chest_ExerciseNameForBodyWork_Spinner;
    private Spinner Arm_ExerciseNameForBodyWork_Spinner;
    private Spinner Shoulder_ExerciseNameForBodyWork_Spinner;
    private Spinner BackMuscle_ExerciseNameForBodyWork_Spinner;
    private Spinner Leg_ExerciseNameForBodyWork_Spinner;
    private String selectedMuscle;
    private String selectedExercise;


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
    protected void OnCreateBodyWork(){
        setContentView(R.layout.activity_new_bodywork_record);
        BodyWork = new SportForBodyWork();
        spinnerInitFlag = false;


        Spinner spinner = (Spinner) findViewById(R.id.MuscleRegion_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.muscle_regions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.Chest_ExerciseNameForBodyWork_Spinner);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.BodyWork_Exercise_For_Chest, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        MuscleRegionSpinner = (Spinner) findViewById(R.id.MuscleRegion_spinner);

        Chest_ExerciseNameForBodyWork_Spinner = (Spinner) findViewById(R.id.Chest_ExerciseNameForBodyWork_Spinner);

        LabelForMuscleRegion = (TextView) findViewById(R.id.LabelForMuscleRegion);
        LabelForBodyWorkTypeExercise = (TextView) findViewById(R.id.LabelForBodyWorkTypeExercise);

        LabelForBodyWorkNumberOfSet = (TextView) findViewById(R.id.LabelForBodyWorkNumberOfSet);
        LabelForBodyWorkNumberOfSet.setVisibility(View.INVISIBLE);

        InputBodyWorkNumberOfSet = (EditText) findViewById(R.id.InputBodyWorkNumberOfSet);
        InputBodyWorkNumberOfSet.setVisibility(View.INVISIBLE);

        LabelForBodyWorkNumberOfRepetition = (TextView) findViewById(R.id.LabelForBodyWorkNumberOfRepetition);
        LabelForBodyWorkNumberOfRepetition.setVisibility(View.INVISIBLE);

        InputBodyWorkNumberOfRepetition = (EditText) findViewById(R.id.InputBodyWorkNumberOfRepetition);
        InputBodyWorkNumberOfRepetition.setVisibility(View.INVISIBLE);

        LabelForDateOfBodyWork = (TextView) findViewById(R.id.LabelForDateOfBodyWork);
        LabelForDateOfBodyWork.setVisibility(View.INVISIBLE);

        InputDateForBodyWorkRecord = (TextView) findViewById(R.id.InputDateForBodyWorkRecord);
        InputDateForBodyWorkRecord.setVisibility(View.INVISIBLE);

        submitButtonForBodyWorkRecord = (Button) findViewById(R.id.submitButtonForBodyWorkRecord);
        submitButtonForBodyWorkRecord.setVisibility(View.INVISIBLE);

        MuscleRegionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMuscle = String.valueOf(MuscleRegionSpinner.getSelectedItem());

                if (selectedMuscle.equals("Select Muscle Region")) {
                    LabelForBodyWorkTypeExercise.setVisibility(View.INVISIBLE);
                    LabelForBodyWorkNumberOfSet.setVisibility(View.INVISIBLE);
                    InputBodyWorkNumberOfSet.setVisibility(View.INVISIBLE);
                    LabelForBodyWorkNumberOfRepetition.setVisibility(View.INVISIBLE);
                    InputBodyWorkNumberOfRepetition.setVisibility(View.INVISIBLE);
                    LabelForDateOfBodyWork.setVisibility(View.INVISIBLE);
                    submitButtonForBodyWorkRecord.setVisibility(View.INVISIBLE);
                    InputDateForBodyWorkRecord.setVisibility(View.INVISIBLE);

                    if (spinnerInitFlag == true) { //if the program applied the steps for the first time
                        Chest_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                        Arm_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                        Shoulder_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                        BackMuscle_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                        Leg_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    }
                }
                else if (selectedMuscle.equals("Chest")) {

                    LabelForBodyWorkTypeExercise.setVisibility(View.VISIBLE);
                    Chest_ExerciseNameForBodyWork_Spinner.setVisibility(View.VISIBLE);

                    Arm_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Shoulder_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    BackMuscle_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Leg_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    selectedExercise = String.valueOf(Chest_ExerciseNameForBodyWork_Spinner.getSelectedItem());
                    makePortionVisible();

                    Chest_ExerciseNameForBodyWork_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedExercise = String.valueOf(Chest_ExerciseNameForBodyWork_Spinner.getSelectedItem());
                        }



                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }
                spinnerInitFlag = true;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {return;}
        });








    }
    public void makePortionVisible() {
        LabelForBodyWorkNumberOfSet.setVisibility(View.VISIBLE);
        InputBodyWorkNumberOfSet.setVisibility(View.VISIBLE);
        LabelForBodyWorkNumberOfRepetition.setVisibility(View.VISIBLE);
        InputBodyWorkNumberOfRepetition.setVisibility(View.VISIBLE);
        LabelForDateOfBodyWork.setVisibility(View.VISIBLE);
        submitButtonForBodyWorkRecord.setVisibility(View.VISIBLE);
        InputDateForBodyWorkRecord.setVisibility(View.VISIBLE);
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
    dateSetForCardio = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;  // since index starts from 0
                String date1 = year + "-" + month + "-" + day;
                editExerciseDateForCardio.setText(date1);
            }
        };

    submitForCardio = (Button) findViewById(R.id.button_submitForCardioRecord);
    submitForCardio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            submitToCardio();
            FirebaseTransaction.addCardio(Cardio);

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

