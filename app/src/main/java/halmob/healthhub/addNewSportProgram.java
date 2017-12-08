package halmob.healthhub;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import halmob.healthhub.Models.SportProgramForBodyWork;
import halmob.healthhub.Models.SportProgramForCardio;
import halmob.healthhub.Models.SportForCardio;

public class addNewSportProgram extends AppCompatActivity {
    private Button cardioButton;
    private Button bodyWorkButton;

    SportProgramForBodyWork ProgramBodyWork;
    private boolean spinnerInitFlagForBodyWork;
    private Spinner MuscleRegionSpinner;
    private Spinner Chest_ExerciseNameForBodyWork_Spinner;
    private Spinner Arm_ExerciseNameForBodyWork_Spinner;
    private Spinner Shoulder_ExerciseNameForBodyWork_Spinner;
    private Spinner Leg_ExerciseNameForBodyWork_Spinner;
    private Spinner BodyWorkProgramFrequency_Spinner;
    private String selectedMuscle;
    private String selectedExercise;
    private String selectedFrequencyForBodyWork;
    private TextView LabelForMuscleRegion;
    private TextView LabelForBodyWorkTypeExercise;
    private TextView LabelForBodyWorkNumberOfSet;
    private TextView LabelForBodyWorkNumberOfRepetition;
    private TextView LabelForBodyWorkProgramFrequency;
    private Button submitButtonForBodyWorkRecord;
    private EditText InputBodyWorkNumberOfSet;
    private EditText InputBodyWorkNumberOfRepetition;


    SportProgramForCardio ProgramCardio;
    private Spinner CardioExercises_Spinner;
    private Spinner CardioProgramFrequency_Spinner;
    private TextView LabelForCardioTypeExercise;
    private TextView LabelForCardioProgramFrequency;
    private TextView LabelForCardioMinute;
    private EditText InputCardioMinute;
    private Button submitButtonForCardioRecord;
    private boolean spinnerInitFlagForCardio;
    private String selectedCardioExercise;
    private String selectedFrequencyForCardio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sport_program);

        cardioButton = findViewById(R.id.Cardio);
        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_new_program_cardio);
                OnCreateCardio();
            }
        });

        bodyWorkButton = findViewById(R.id.BodyWork);
        bodyWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_new_program_bodywork);

            }
        });
    }
    ////////////////////////////////////////////////////
    public void OnCreateCardio(){
        setContentView(R.layout.activity_new_program_cardio);
        ProgramCardio = new SportProgramForCardio();
        spinnerInitFlagForCardio = false;

        Spinner spinnerForCardioExercise = (Spinner) findViewById(R.id.CardioExercises_Spinner);

        ArrayAdapter<CharSequence> adapterForCardioExercise = ArrayAdapter.createFromResource(this,
                R.array.Cardio_Exercise, android.R.layout.simple_spinner_item);
        adapterForCardioExercise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForCardioExercise.setAdapter(adapterForCardioExercise);

        CardioExercises_Spinner = (Spinner) findViewById(R.id.CardioExercises_Spinner);

        Spinner spinner2 = (Spinner) findViewById(R.id.CardioProgramFrequency_Spinner);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Frequency, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        CardioProgramFrequency_Spinner = (Spinner) findViewById(R.id.CardioProgramFrequency_Spinner);

        LabelForCardioTypeExercise = (TextView) findViewById(R.id.LabelForCardioTypeExercise);

        LabelForCardioProgramFrequency =(TextView) findViewById(R.id.LabelForCardioProgramFrequency);
        LabelForCardioProgramFrequency.setVisibility(View.INVISIBLE);

        LabelForCardioMinute = (TextView) findViewById(R.id.LabelForCardioMinute);
        LabelForCardioMinute.setVisibility(View.INVISIBLE);

        InputCardioMinute = (EditText) findViewById(R.id.InputCardioMinute);
        InputCardioMinute.setVisibility(View.INVISIBLE);

        submitButtonForCardioRecord = (Button) findViewById(R.id.submitButtonForCardioRecord);
        submitButtonForCardioRecord.setVisibility(View.INVISIBLE);

        CardioExercises_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCardioExercise = String.valueOf(CardioExercises_Spinner.getSelectedItem());
                if (selectedCardioExercise.equals("Select Cardio Exercise")) {
                    LabelForCardioProgramFrequency.setVisibility(View.INVISIBLE);
                    InputCardioMinute.setVisibility(View.INVISIBLE);
                    LabelForCardioMinute.setVisibility(View.INVISIBLE);
                    submitButtonForCardioRecord.setVisibility(View.INVISIBLE);
                    if (spinnerInitFlagForCardio == true) { //if the program applied the steps for the first time
                        CardioProgramFrequency_Spinner.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    CardioProgramFrequency_Spinner.setVisibility(View.VISIBLE);
                    makePortionVisibleForCardioProgram();
                    CardioProgramFrequency_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedFrequencyForCardio = String.valueOf(CardioProgramFrequency_Spinner.getSelectedItem());
                        }
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });

                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {return;}
        });
        submitButtonForCardioRecord = (Button) findViewById(R.id.submitButtonForCardioRecord);
        submitButtonForCardioRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitToCardio();
                FirebaseTransaction.addCardioProgram(ProgramCardio);

                Toast.makeText(getApplicationContext(),
                        "Sport Record is saved successfully!",
                        Toast.LENGTH_LONG).show();

                Intent intent1 = new Intent(addNewSportProgram.this, Sports_Activity.class);
                startActivity(intent1);
                finish();

            }
        });
        bodyWorkButton = findViewById(R.id.BodyWork);
        bodyWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_new_program_bodywork);

            }
        });


    }
    public void submitToCardio(){

            String input1 = selectedCardioExercise.toString();
            String input2 = InputCardioMinute.getText().toString();
            String input3 = selectedFrequencyForCardio.toString();

            createCardioProgram(input1, input2, input3 );
    }
    public void createCardioProgram(String s1,String s2,String s3){
        ProgramCardio.setNameOfExerciseForCardio(s1);
        ProgramCardio.setMinuteOfExerciseForCardio(s2);
        ProgramCardio.setFrequencyForCardio(s3);
    }
    public void makePortionVisibleForCardioProgram(){

        LabelForCardioProgramFrequency.setVisibility(View.VISIBLE);
        InputCardioMinute.setVisibility(View.VISIBLE);
        LabelForCardioMinute.setVisibility(View.VISIBLE);
        submitButtonForCardioRecord.setVisibility(View.VISIBLE);
    }
    ////////////////////////////////////////////////////
    protected void OnCreateBodyWork(){
        setContentView(R.layout.activity_new_program_bodywork);
        ProgramBodyWork = new SportProgramForBodyWork();
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

        Spinner spinner6 = (Spinner) findViewById(R.id.BodyWorkProgramFrequency_Spinner);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.Frequency, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter6);


        MuscleRegionSpinner = (Spinner) findViewById(R.id.MuscleRegion_spinner);
        Chest_ExerciseNameForBodyWork_Spinner = (Spinner) findViewById(R.id.Chest_ExerciseNameForBodyWork_Spinner);
        Arm_ExerciseNameForBodyWork_Spinner = (Spinner) findViewById(R.id.Arm_ExerciseNameForBodyWork_Spinner);
        Shoulder_ExerciseNameForBodyWork_Spinner = (Spinner) findViewById(R.id.Shoulder_ExerciseNameForBodyWork_Spinner);
        Leg_ExerciseNameForBodyWork_Spinner = (Spinner) findViewById(R.id.Leg_ExerciseNameForBodyWork_Spinner);
        BodyWorkProgramFrequency_Spinner = (Spinner) findViewById(R.id.BodyWorkProgramFrequency_Spinner);


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
                    LabelForBodyWorkProgramFrequency.setVisibility(View.INVISIBLE);
                    BodyWorkProgramFrequency_Spinner.setVisibility(View.INVISIBLE);
                    submitButtonForBodyWorkRecord.setVisibility(View.INVISIBLE);


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
                    BodyWorkProgramFrequency_Spinner.setVisibility(View.VISIBLE);
                    BodyWorkProgramFrequency_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedFrequencyForBodyWork = String.valueOf(BodyWorkProgramFrequency_Spinner.getSelectedItem());
                        }
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });

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
                    BodyWorkProgramFrequency_Spinner.setVisibility(View.VISIBLE);
                    BodyWorkProgramFrequency_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedFrequencyForBodyWork = String.valueOf(BodyWorkProgramFrequency_Spinner.getSelectedItem());
                        }
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });

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
                    BodyWorkProgramFrequency_Spinner.setVisibility(View.VISIBLE);
                    BodyWorkProgramFrequency_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedFrequencyForBodyWork = String.valueOf(BodyWorkProgramFrequency_Spinner.getSelectedItem());
                        }
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });

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
                    BodyWorkProgramFrequency_Spinner.setVisibility(View.VISIBLE);
                    BodyWorkProgramFrequency_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedFrequencyForBodyWork = String.valueOf(BodyWorkProgramFrequency_Spinner.getSelectedItem());
                        }
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });

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


        submitButtonForBodyWorkRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitToBodyWork();
                FirebaseTransaction.addBodyWorkProgram(ProgramBodyWork);

                Toast.makeText(getApplicationContext(),
                        "Sport Record is saved successfully!",
                        Toast.LENGTH_LONG).show();

                Intent intent1 = new Intent(addNewSportProgram.this, Sports_Activity.class);
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
        LabelForBodyWorkProgramFrequency.setVisibility(View.VISIBLE);
        submitButtonForBodyWorkRecord.setVisibility(View.VISIBLE);
    }
    public void createBodyWorkExercise(String s1, String s2, String s3, String s4) {
        ProgramBodyWork.setNameOfExerciseForBodyWork(s1);
        ProgramBodyWork.setNumberOfSetForBodyWork(s2);
        ProgramBodyWork.setNumberOfRepetitionForBodyWork(s3);
        ProgramBodyWork.setFrequencyForBodyWork(s4);
    }
    public void submitToBodyWork() {
        String input1 = selectedExercise.toString();
        String input2 = InputBodyWorkNumberOfSet.getText().toString();
        String input3 = InputBodyWorkNumberOfRepetition.getText().toString();
        String input4 = selectedFrequencyForBodyWork.toString();

        createBodyWorkExercise(input1, input2, input3, input4);

    }
}


