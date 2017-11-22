package halmob.healthhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import halmob.healthhub.Models.InsulinDose;

public class InsulinActivity extends AppCompatActivity {

    private Spinner insulinTypeSpinner;
    private EditText editTextInsulinDose;
    private EditText editTextDate;
    private EditText editTextTime;

    private Button buttonSubmit;

    InsulinDose NewInsulinDose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insulin);

        editTextInsulinDose = (EditText) findViewById(R.id.insulin_dose_input);
        // editTextDate = (EditText) findViewById(R.id.editText_3);
        // editTextTime = (EditText) findViewById(R.id.editText_4);

        //Spinner definition
        Spinner spinner = (Spinner) findViewById(R.id.insulin_type_input);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.insulin_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        insulinTypeSpinner = (Spinner) findViewById(R.id.insulin_type_input);

        NewInsulinDose = new InsulinDose();

        buttonSubmit = (Button) findViewById(R.id.button_submit);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitInsulinDose();
                FirebaseTransaction.addInsulinDose(NewInsulinDose);
                Toast.makeText(getApplicationContext(),
                        "Insulin Dose Record is saved successfully!",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }



    public void submitInsulinDose()
    {
        String insulinType = String.valueOf(insulinTypeSpinner.getSelectedItem());

        /* Use the lines below if you need integer input!
        int intInsulinType = -1;

        try {
            intInsulinType = Integer.parseInt(stringInsulinType);
        }
        catch (NumberFormatException e) {

        }
        */

        String InsulinDose = editTextInsulinDose.getText().toString();

        /*
        If you need to convert a string to float use the code below!

        float floatInsulinDose = -1;

        try {
            floatInsulinDose = NumberFormat.getInstance().parse(editTextInsulinDose.getText().toString()).floatValue();
        }
        catch (ParseException e) {

        }
        */
        createInsulinDose(insulinType, InsulinDose);

    }

    public void createInsulinDose(String insulinType, String insulinDose)
    {
        NewInsulinDose.setDate(); //set current date
        NewInsulinDose.setTime(); //set current time
        NewInsulinDose.setInsulinType(insulinType);
        NewInsulinDose.setAppliedDose(insulinDose);
    }

    //kullanıcının ilaçlarını okumak için aşağıdaki iki satırlık kodu kullan. Sonuçlar drugsRead fonksiyonuna düşecek.
        /*FirebaseTransaction.setDrugListenerListener(this);
        FirebaseTransaction.getDrugs();*/
    //@Override
    public void insulinDosesRead(List<InsulinDose> insulinDoseList){
        //kullanıcıyla ilgili bütün ilaçlar drugList'in içinde
        //istediğin gibi kullan
    }
}
