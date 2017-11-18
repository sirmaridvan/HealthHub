package halmob.healthhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import halmob.healthhub.Models.BloodSugar;
import halmob.healthhub.EventListeners.BloodSugarListener;

public class BloodSugarActivity extends AppCompatActivity {
    private EditText editTextSugarValue;
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
        editTextHungerSituation = (EditText) findViewById(R.id.hunger_situation_input);
        // editTextDate = (EditText) findViewById(R.id.editText_3);
        // editTextTime = (EditText) findViewById(R.id.editText_4);
        editTextExtraNotes = (EditText) findViewById(R.id.extra_notes_input);

        NewBloodSugar = new BloodSugar();

        buttonSubmit = (Button) findViewById(R.id.blood_sugar_submit_button);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitBloodSugar();
                FirebaseTransaction.addBloodSugar(NewBloodSugar);
                Toast.makeText(getApplicationContext(),
                        "Data: "
                                + NewBloodSugar.getBloodSugarValue()
                                + " " + NewBloodSugar.getHungerSituation()
                                + " " + NewBloodSugar.getDate()
                                + " " + NewBloodSugar.getTime()
                                + " " + NewBloodSugar.getExtraNotes(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }



    public void submitBloodSugar()
    {
        String hungerSituation = editTextHungerSituation.getText().toString();

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


