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

public class addNewSportRecordActivity extends AppCompatActivity implements StaticBodyWorkListener {
    private boolean spinnerInitFlag;
    private Spinner muscleRegionForBodyWorkSpinner;
    private Spinner backMuscleForBodyWorkSpinner;
    private Spinner chestMuscleForBodyWorkSpinner;
    private Spinner legMuscleForBodyWorkSpinner;
    private Spinner ArmMuscleForBodyWorkSpinner;
    private Spinner shoulderMuscleForBodyWorkSpinner;
    private String selectedMuscleRegion;
    private String selectedExerciseForBodyWork;
    private ArrayList<StaticExerciseForBodyWork> allStaticBodyWorkList = new ArrayList<>();;

    private Button cardioButton;
    private Button bodyWorkButton;
    private Button submitForBodyWork;
    private Button submitForCardio;

    private DatePickerDialog.OnDateSetListener dateSetForBodyWork;
    private DatePickerDialog.OnDateSetListener dateSetForCardio;


    private TextView LabelForBodyWorkTypeExercise;
    private TextView LabelForBodyWorkNumberOfSet;
    private TextView LabelForBodyWorkNumberOfRepetition;
    private TextView LabelBodyWorkForWeight;

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
        FirebaseTransaction.setStaticBodyWorkListener(this);
        FirebaseTransaction.getStaticBodyWork();
        spinnerInitFlag = false;

    BodyWork = new SportForBodyWork();
    Spinner spinner = (Spinner) findViewById(R.id.MuscleRegion_spinner);

    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.muscle_regions, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);

    muscleRegionForBodyWorkSpinner = (Spinner) findViewById(R.id.MuscleRegion_spinner);

    LabelForBodyWorkNumberOfSet = (TextView) findViewById(R.id.LabelForBodyWorkNumberOfSet);
    LabelForBodyWorkNumberOfSet.setVisibility(View.INVISIBLE);
    LabelForBodyWorkNumberOfRepetition = (TextView) findViewById(R.id.LabelForBodyWorkNumberOfRepetition);
    LabelForBodyWorkNumberOfRepetition.setVisibility(View.INVISIBLE);
    LabelBodyWorkForWeight = (TextView) findViewById(R.id.LabelBodyWorkForWeight);
    LabelBodyWorkForWeight.setVisibility(View.INVISIBLE);

    editNumberOfSetsForBodywork = (EditText) findViewById(R.id.InputBodyWorkNumberOfSet);
    editNumberOfSetsForBodywork.setVisibility(View.INVISIBLE);

    editNumberOfRepetitionForBodyWork = (EditText) findViewById(R.id.InputBodyWorkNumberOfRepetition);
    editNumberOfRepetitionForBodyWork.setVisibility(View.INVISIBLE);

    editWeightForBodyWork = (EditText) findViewById(R.id.InputBodyWorkWeight);
    editWeightForBodyWork.setVisibility(View.INVISIBLE);

    editExerciseDateForBodyWork = (TextView) findViewById(R.id.InputDateForBodyWorkRecord);
    editExerciseDateForBodyWork.setVisibility(View.INVISIBLE);

    submitForBodyWork = (Button) findViewById(R.id.button_submitForBodyWorkRecord);
    submitForBodyWork.setVisibility(View.INVISIBLE);

        muscleRegionForBodyWorkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMuscleRegion = String.valueOf(muscleRegionForBodyWorkSpinner.getSelectedItem());
                if(selectedMuscleRegion.equals("Select Muscle Region")) {
                    editNumberOfSetsForBodywork.setVisibility(View.INVISIBLE);
                    editNumberOfRepetitionForBodyWork.setVisibility(View.INVISIBLE);
                    editWeightForBodyWork.setVisibility(View.INVISIBLE);
                    editExerciseDateForBodyWork.setVisibility(View.INVISIBLE);

                    if(spinnerInitFlag == true) { //if the program applied the steps for the first time
                        backMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                        chestMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                        legMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                        ArmMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                        shoulderMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    }

                }
                else if( selectedMuscleRegion.equals("Chest")) {
                    chestMuscleForBodyWorkSpinner.setVisibility(View.VISIBLE);
                    legMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    ArmMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    shoulderMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    backMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    selectedExerciseForBodyWork = String.valueOf(chestMuscleForBodyWorkSpinner.getSelectedItem());
                    makePortionVisibleForBodyWork();

                    chestMuscleForBodyWorkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedExerciseForBodyWork = String.valueOf(chestMuscleForBodyWorkSpinner.getSelectedItem());
                        }
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }
                else if(selectedMuscleRegion.equals("Leg")){
                    legMuscleForBodyWorkSpinner.setVisibility(View.VISIBLE);
                    backMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    chestMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    ArmMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    shoulderMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    selectedExerciseForBodyWork = String.valueOf(legMuscleForBodyWorkSpinner.getSelectedItem());
                    makePortionVisibleForBodyWork();

                    legMuscleForBodyWorkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedExerciseForBodyWork = String.valueOf(legMuscleForBodyWorkSpinner.getSelectedItem());
                        }
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }
                else if(selectedMuscleRegion.equals("Arm")){
                    legMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    backMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    chestMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    ArmMuscleForBodyWorkSpinner.setVisibility(View.VISIBLE);
                    shoulderMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    selectedExerciseForBodyWork = String.valueOf(ArmMuscleForBodyWorkSpinner.getSelectedItem());
                    makePortionVisibleForBodyWork();

                    ArmMuscleForBodyWorkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedExerciseForBodyWork = String.valueOf(ArmMuscleForBodyWorkSpinner.getSelectedItem());
                        }
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }
                else if(selectedMuscleRegion.equals("Shoulder")){
                    legMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    backMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    chestMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    ArmMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    shoulderMuscleForBodyWorkSpinner.setVisibility(View.VISIBLE);
                    selectedExerciseForBodyWork = String.valueOf(shoulderMuscleForBodyWorkSpinner.getSelectedItem());
                    makePortionVisibleForBodyWork();

                    shoulderMuscleForBodyWorkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedExerciseForBodyWork = String.valueOf(shoulderMuscleForBodyWorkSpinner.getSelectedItem());
                        }
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }
                else if(selectedMuscleRegion.equals("Back Muscle")){
                    legMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    backMuscleForBodyWorkSpinner.setVisibility(View.VISIBLE);
                    chestMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    ArmMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    shoulderMuscleForBodyWorkSpinner.setVisibility(View.INVISIBLE);
                    selectedExerciseForBodyWork = String.valueOf(backMuscleForBodyWorkSpinner.getSelectedItem());
                    makePortionVisibleForBodyWork();

                    backMuscleForBodyWorkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedExerciseForBodyWork = String.valueOf(backMuscleForBodyWorkSpinner.getSelectedItem());
                        }
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }
                spinnerInitFlag = true;
            }



            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

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


    submitForBodyWork.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            submitToBodyWork();
            FirebaseTransaction.addBodyWork(BodyWork);

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
        String input1 = selectedExerciseForBodyWork.toString();
        String input2 = editNumberOfSetsForBodywork.getText().toString();
        String input3 = editNumberOfRepetitionForBodyWork.getText().toString();
        String input4 = editWeightForBodyWork.getText().toString();
        String input5 = editExerciseDateForBodyWork.getText().toString();

        createBodyWorkExercise(input1, input2, input3, input4, input5);

    }
    public void makePortionVisibleForBodyWork() {
        LabelForBodyWorkNumberOfSet.setVisibility(View.VISIBLE);
        LabelForBodyWorkNumberOfRepetition.setVisibility(View.VISIBLE);
        LabelBodyWorkForWeight.setVisibility(View.VISIBLE);
        editNumberOfSetsForBodywork.setVisibility(View.VISIBLE);
        editNumberOfRepetitionForBodyWork.setVisibility(View.VISIBLE);
        editWeightForBodyWork.setVisibility(View.VISIBLE);
        editExerciseDateForBodyWork.setVisibility(View.VISIBLE);
    }
    public void createBodyWorkExercise(String s1, String s2, String s3, String s4, String s5) {
        BodyWork.setNameOfExerciseForBodyWork(s1);
        BodyWork.setNumberOfSetForBodyWork(s2);
        BodyWork.setNumberOfRepetitionForBodyWork(s3);
        BodyWork.setWeightForBodyWork(s4);
        BodyWork.setExerciseDateForBodyWork(s5);
    }
    public void StaticBodyWorkRead(List<StaticExerciseForBodyWork> StaticExerciseForBodyWork) {
        Spinner spinner2 = (Spinner) findViewById(R.id.fruit_food_name_spinner);
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
            OnCreateBodyWork();
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

