package halmob.healthhub;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import halmob.healthhub.Models.BloodSugar;
import halmob.healthhub.EventListeners.BloodSugarListener;

public class BloodSugarActivity extends AppCompatActivity implements TextWatcher {
    private EditText editTextSugarValue;
    private Spinner hungerSituationSpinner;
    private EditText editTextHungerSituation;
    private EditText editTextDate;
    private EditText editTextTime;
    private EditText editTextExtraNotes;

    private Button buttonSubmit;

    BloodSugar NewBloodSugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar);

        editTextSugarValue = (EditText) findViewById(R.id.blood_sugar_input);
        editTextSugarValue.addTextChangedListener(this);


        //Spinner definition
        Spinner spinner = (Spinner) findViewById(R.id.hunger_situation_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hunger_situation_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        hungerSituationSpinner = (Spinner) findViewById(R.id.hunger_situation_spinner);

        // editTextDate = (EditText) findViewById(R.id.editText_3);
        // editTextTime = (EditText) findViewById(R.id.editText_4);
        editTextExtraNotes = (EditText) findViewById(R.id.extra_notes_input);

        NewBloodSugar = new BloodSugar();




        buttonSubmit = (Button) findViewById(R.id.blood_sugar_submit_button);
        buttonSubmit.setEnabled(false);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitBloodSugar();
                FirebaseTransaction.addBloodSugar(NewBloodSugar);
                Toast.makeText(getApplicationContext(),
                        "Blood Sugar Record is saved successfully!",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int intS = 0;
        if(s.toString().trim().length() != 0)
            intS = Integer.parseInt(s.toString());
        if(s.toString().trim().length()==0){
            buttonSubmit.setEnabled(false);
            editTextSugarValue.setError("This field can not be empty!");
        }

        else if (s.toString().trim().length() >= 4 && s.toString().contains(".") == false ){
            buttonSubmit.setEnabled(false);
            editTextSugarValue.setError("The entered dose is too high!");
        }

        else if(intS >= 600 || intS < 30 ) {
            buttonSubmit.setEnabled(false);
            editTextSugarValue.setError("The entered dose is not valid!");
        }

        else {
            buttonSubmit.setEnabled(true);
        }

    }

    public void afterTextChanged(Editable s) {
    }



    public void submitBloodSugar()
    {
        String hungerSituation = String.valueOf(hungerSituationSpinner.getSelectedItem());

        String stringBloodSugarValue = editTextSugarValue.getText().toString();
        int intBloodSugarValue = -1;

        try {
            intBloodSugarValue = Integer.parseInt(stringBloodSugarValue);
        }
        catch (NumberFormatException e) {

        }

        String extraNotes = editTextExtraNotes.getText().toString();


        createBloodSugarRecord(intBloodSugarValue, hungerSituation, extraNotes);

    }

    public void createBloodSugarRecord(int bloodSugarValue, String hungerSituation, String extraNotes)
    {
        NewBloodSugar.setDate(); //set current date
        NewBloodSugar.setTime(); //set current time
        NewBloodSugar.setBloodSugarValue(bloodSugarValue);
        NewBloodSugar.setHungerSituation(hungerSituation);
        NewBloodSugar.setExtraNotes(extraNotes);
    }

    //kullanıcının ilaçlarını okumak için aşağıdaki iki satırlık kodu kullan. Sonuçlar drugsRead fonksiyonuna düşecek.
        /*FirebaseTransaction.setDrugListenerListener(this);
        FirebaseTransaction.getDrugs();*/
    //@Override
    public void bloodSugarsRead(List<BloodSugar> bloodSugarList){
        //kullanıcıyla ilgili bütün ilaçlar drugList'in içinde
        //istediğin gibi kullan
    }

}


