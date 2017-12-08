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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




import halmob.healthhub.Models.SportForBodyWork;
import halmob.healthhub.Models.SportForCardio;


public class addNewSportRecordActivity extends AppCompatActivity {


    private Button cardioButton;
    private Button bodyWorkButton;

    private DatePickerDialog.OnDateSetListener dateSetForCardio;
    private DatePickerDialog.OnDateSetListener dateSetForBodyWork;

    private EditText editExerciseNameForCardio;
    private EditText editMinuteOfExerciseForCardio;
    private EditText editBurnedCaloriesForCardio;
    private TextView editExerciseDateForCardio;

    private TextView LabelForMuscleRegion;
    private TextView LabelForBodyWorkTypeExercise;
    private TextView LabelForBodyWorkNumberOfSet;
    private TextView LabelForBodyWorkNumberOfRepetition;
    private TextView LabelForDateOfBodyWork;
    private TextView LabelForBodyWorkWeight;
    private EditText InputBodyWorkWeight;
    private TextView InputDateForBodyWorkRecord;
    private Button submitButtonForBodyWorkRecord;
    private EditText InputBodyWorkNumberOfSet;
    private EditText InputBodyWorkNumberOfRepetition;
    private boolean spinnerInitFlagForBodyWork;
    private Spinner MuscleRegionSpinner;
    private Spinner Chest_ExerciseNameForBodyWork_Spinner;
    private Spinner Arm_ExerciseNameForBodyWork_Spinner;
    private Spinner Shoulder_ExerciseNameForBodyWork_Spinner;
    private Spinner Leg_ExerciseNameForBodyWork_Spinner;
    private String selectedMuscle;
    private String selectedExercise;

    private TextView LabelForCardioTypeExercise;
    private TextView LabelForCardioMinute;
    private TextView LabelForCardioBurnedCalories;
    private TextView LabelForDateOfCardio;
    private String selectedCardioExercise;
    private Spinner CardioExercises_Spinner;
    private boolean spinnerInitFlagForCardio;
    private EditText InputCardioMinute;
    private EditText InputCardioBurnedCalories;
    private TextView InputDateForCardiRecordRecord;
    private Button submitButtonForCardioRecord;



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
                OnCreateCardio();
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
        spinnerInitFlagForBodyWork = false;


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

        Spinner spinner3 = (Spinner) findViewById(R.id.Leg_ExerciseNameForBodyWork_Spinner);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.BodyWork_Exercise_For_Leg, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Spinner spinner4 = (Spinner) findViewById(R.id.Shoulder_ExerciseNameForBodyWork_Spinner);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.BodyWork_Exercise_For_Shoulder, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        Spinner spinner5 = (Spinner) findViewById(R.id.Arm_ExerciseNameForBodyWork_Spinner);

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.BodyWork_Exercise_For_Arm, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);


        MuscleRegionSpinner = (Spinner) findViewById(R.id.MuscleRegion_spinner);
        Chest_ExerciseNameForBodyWork_Spinner = (Spinner) findViewById(R.id.Chest_ExerciseNameForBodyWork_Spinner);
        Arm_ExerciseNameForBodyWork_Spinner = (Spinner) findViewById(R.id.Arm_ExerciseNameForBodyWork_Spinner);
        Shoulder_ExerciseNameForBodyWork_Spinner = (Spinner) findViewById(R.id.Shoulder_ExerciseNameForBodyWork_Spinner);
        Leg_ExerciseNameForBodyWork_Spinner = (Spinner) findViewById(R.id.Leg_ExerciseNameForBodyWork_Spinner);


        LabelForMuscleRegion = (TextView) findViewById(R.id.LabelForMuscleRegion);
        LabelForBodyWorkTypeExercise = (TextView) findViewById(R.id.LabelForBodyWorkTypeExercise);

        LabelForBodyWorkNumberOfSet = (TextView) findViewById(R.id.LabelForBodyWorkNumberOfSet);
        LabelForBodyWorkNumberOfSet.setVisibility(View.INVISIBLE);

        LabelForBodyWorkWeight = (TextView) findViewById(R.id.LabelForBodyWorkWeight);
        LabelForBodyWorkWeight.setVisibility(View.INVISIBLE);

        InputBodyWorkWeight = (EditText) findViewById(R.id.InputBodyWorkWeight);
        InputBodyWorkWeight.setVisibility(View.INVISIBLE);


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
                    LabelForBodyWorkWeight.setVisibility(View.INVISIBLE);
                    InputBodyWorkWeight.setVisibility(View.INVISIBLE);

                    if (spinnerInitFlagForBodyWork == true) { //if the program applied the steps for the first time
                        Chest_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                        Arm_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                        Shoulder_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                        Leg_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    }
                }
                else if (selectedMuscle.equals("Chest")) {

                    LabelForBodyWorkTypeExercise.setVisibility(View.VISIBLE);
                    Chest_ExerciseNameForBodyWork_Spinner.setVisibility(View.VISIBLE);
                    Arm_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Shoulder_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Leg_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    selectedExercise = String.valueOf(Chest_ExerciseNameForBodyWork_Spinner.getSelectedItem());
                    makePortionVisibleForBodyWork();

                    Chest_ExerciseNameForBodyWork_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedExercise = String.valueOf(Chest_ExerciseNameForBodyWork_Spinner.getSelectedItem());
                        }



                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }
                else if (selectedMuscle.equals("Leg")) {

                    LabelForBodyWorkTypeExercise.setVisibility(View.VISIBLE);
                    Chest_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Arm_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Shoulder_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Leg_ExerciseNameForBodyWork_Spinner.setVisibility(View.VISIBLE);
                    selectedExercise = String.valueOf(Leg_ExerciseNameForBodyWork_Spinner.getSelectedItem());
                    makePortionVisibleForBodyWork();

                    Leg_ExerciseNameForBodyWork_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedExercise = String.valueOf(Leg_ExerciseNameForBodyWork_Spinner.getSelectedItem());
                        }



                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }
                else if (selectedMuscle.equals("Arm")) {

                    LabelForBodyWorkTypeExercise.setVisibility(View.VISIBLE);
                    Chest_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Arm_ExerciseNameForBodyWork_Spinner.setVisibility(View.VISIBLE);
                    Shoulder_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Leg_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    selectedExercise = String.valueOf(Arm_ExerciseNameForBodyWork_Spinner.getSelectedItem());
                    makePortionVisibleForBodyWork();

                    Arm_ExerciseNameForBodyWork_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedExercise = String.valueOf(Arm_ExerciseNameForBodyWork_Spinner.getSelectedItem());
                        }



                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }
                else if (selectedMuscle.equals("Shoulder")) {

                    LabelForBodyWorkTypeExercise.setVisibility(View.VISIBLE);
                    Chest_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Arm_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    Shoulder_ExerciseNameForBodyWork_Spinner.setVisibility(View.VISIBLE);
                    Leg_ExerciseNameForBodyWork_Spinner.setVisibility(View.INVISIBLE);
                    selectedExercise = String.valueOf(Shoulder_ExerciseNameForBodyWork_Spinner.getSelectedItem());
                    makePortionVisibleForBodyWork();

                    Shoulder_ExerciseNameForBodyWork_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedExercise = String.valueOf(Shoulder_ExerciseNameForBodyWork_Spinner.getSelectedItem());
                        }



                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }
                spinnerInitFlagForBodyWork = true;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {return;}
        });
        InputDateForBodyWorkRecord.setOnClickListener(new View.OnClickListener() {

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
                InputDateForBodyWorkRecord.setText(date1);
            }
        };


        submitButtonForBodyWorkRecord.setOnClickListener(new View.OnClickListener() {
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
    public void makePortionVisibleForBodyWork() {
        LabelForBodyWorkNumberOfSet.setVisibility(View.VISIBLE);
        InputBodyWorkNumberOfSet.setVisibility(View.VISIBLE);
        LabelForBodyWorkNumberOfRepetition.setVisibility(View.VISIBLE);
        InputBodyWorkNumberOfRepetition.setVisibility(View.VISIBLE);
        LabelForDateOfBodyWork.setVisibility(View.VISIBLE);
        submitButtonForBodyWorkRecord.setVisibility(View.VISIBLE);
        InputDateForBodyWorkRecord.setVisibility(View.VISIBLE);
        LabelForBodyWorkWeight.setVisibility(View.VISIBLE);
        InputBodyWorkWeight.setVisibility(View.VISIBLE);
    }
    public void createBodyWorkExercise(String s1, String s2, String s3, String s4, String s5) {
        BodyWork.setNameOfExerciseForBodyWork(s1);
        BodyWork.setNumberOfSetForBodyWork(s2);
        BodyWork.setNumberOfRepetitionForBodyWork(s3);
        BodyWork.setWeightForBodyWork(s4);
        BodyWork.setExerciseDateForBodyWork(s5);
    }
    public void submitToBodyWork() {
        String input1 = selectedExercise.toString();
        String input2 = InputBodyWorkNumberOfSet.getText().toString();
        String input3 = InputBodyWorkNumberOfRepetition.getText().toString();
        String input4 = InputBodyWorkWeight.getText().toString();
        String input5 = InputDateForBodyWorkRecord.getText().toString();

        createBodyWorkExercise(input1, input2, input3, input4, input5);

    }

//////////////////////////////////////////////////////////////////////////////////
    //FOR CARDIO
//////////////////////////////////////////////////////////////////////////////
    protected void OnCreateCardio() {
    setContentView(R.layout.activity_new_cardio_record);

    Cardio = new SportForCardio();

    Spinner spinnerForCardioExercise = (Spinner) findViewById(R.id.CardioExercises_Spinner);

    ArrayAdapter<CharSequence> adapterForCardioExercise = ArrayAdapter.createFromResource(this,
                R.array.Cardio_Exercise, android.R.layout.simple_spinner_item);
    adapterForCardioExercise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerForCardioExercise.setAdapter(adapterForCardioExercise);



    CardioExercises_Spinner = (Spinner) findViewById(R.id.CardioExercises_Spinner);

    LabelForCardioTypeExercise = (TextView) findViewById(R.id.LabelForCardioTypeExercise);

    InputCardioMinute = (EditText) findViewById(R.id.InputCardioMinute);
    InputCardioMinute.setVisibility(View.INVISIBLE);

    InputCardioBurnedCalories = (EditText) findViewById(R.id.InputCardioBurnedCalories);
    InputCardioBurnedCalories.setVisibility(View.INVISIBLE);

    InputDateForCardiRecordRecord = (TextView) findViewById(R.id.InputDateForCardiRecordRecord);
    InputDateForCardiRecordRecord.setVisibility(View.INVISIBLE);

    LabelForDateOfCardio = (TextView) findViewById(R.id.LabelForDateOfCardio);
    LabelForDateOfCardio.setVisibility(View.INVISIBLE);

    LabelForCardioBurnedCalories = (TextView) findViewById(R.id.LabelForCardioBurnedCalories);
    LabelForCardioBurnedCalories.setVisibility(View.INVISIBLE);

    LabelForCardioMinute = (TextView)  findViewById(R.id.LabelForCardioMinute);
    LabelForCardioMinute.setVisibility(View.INVISIBLE);

    submitButtonForCardioRecord = (Button) findViewById(R.id.submitButtonForCardioRecord);
    submitButtonForCardioRecord.setVisibility(View.INVISIBLE);



        CardioExercises_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            selectedCardioExercise = String.valueOf(CardioExercises_Spinner.getSelectedItem());

            if (selectedCardioExercise.equals("Select Cardio Exercise")) {
                InputCardioMinute.setVisibility(View.INVISIBLE);
                InputCardioBurnedCalories.setVisibility(View.INVISIBLE);
                InputDateForCardiRecordRecord.setVisibility(View.INVISIBLE);
                LabelForDateOfCardio.setVisibility(View.INVISIBLE);
                LabelForCardioBurnedCalories.setVisibility(View.INVISIBLE);
                LabelForCardioMinute.setVisibility(View.INVISIBLE);
                submitButtonForCardioRecord.setVisibility(View.INVISIBLE);

            }
            else{
                makePortionVisibleForCardio();

            }
        }
            public void onNothingSelected(AdapterView<?> adapterView) {return;}
    });



    InputDateForCardiRecordRecord.setOnClickListener(new View.OnClickListener() {

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
                InputDateForCardiRecordRecord.setText(date1);
            }
        };
    submitButtonForCardioRecord = (Button) findViewById(R.id.submitButtonForCardioRecord);
    submitButtonForCardioRecord.setOnClickListener(new View.OnClickListener() {
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
    public void submitToCardio() {
        String input1 = selectedCardioExercise.toString();
        String input2 = InputCardioMinute.getText().toString();
        String input3 = InputCardioBurnedCalories.getText().toString();
        String input4 = InputDateForCardiRecordRecord.getText().toString();

        createCardioExercise(input1, input2, input3, input4);

    }
    public void createCardioExercise(String s1, String s2, String s3, String s4) {
        Cardio.setNameOfExerciseForCardio(s1);
        Cardio.setMinuteOfExerciseForCardio(s2);
        Cardio.setBurnedCaloriesForCardio(s3);
        Cardio.setExerciseDateForCardio(s4);
    }
    public void makePortionVisibleForCardio(){
        InputCardioMinute.setVisibility(View.VISIBLE);
        InputCardioBurnedCalories.setVisibility(View.VISIBLE);
        InputDateForCardiRecordRecord.setVisibility(View.VISIBLE);
        LabelForDateOfCardio.setVisibility(View.VISIBLE);
        LabelForCardioBurnedCalories.setVisibility(View.VISIBLE);
        LabelForCardioMinute.setVisibility(View.VISIBLE);
        submitButtonForCardioRecord.setVisibility(View.VISIBLE);
    }
    ////////////////////////////////////////////////////////
}

