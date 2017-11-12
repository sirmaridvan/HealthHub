package halmob.healthhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;

import halmob.healthhub.Models.InsulinDose;

public class InsulinActivity extends AppCompatActivity {

    private EditText editTextInsulinType;
    private EditText editTextInsulinDose;
    private EditText editTextDate;
    private EditText editTextTime;

    private Button buttonSubmit;

    InsulinDose NewInsulinDose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insulin);

        editTextInsulinType = (EditText) findViewById(R.id.insulin_type_input);
        editTextInsulinDose = (EditText) findViewById(R.id.insulin_dose_input);
        // editTextDate = (EditText) findViewById(R.id.editText_3);
        // editTextTime = (EditText) findViewById(R.id.editText_4);

        NewInsulinDose = new InsulinDose();

        buttonSubmit = (Button) findViewById(R.id.button_submit);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitInsulinDose();
                Toast.makeText(getApplicationContext(),
                        "Data: "
                                + NewInsulinDose.getInsulinType()
                                + " " + NewInsulinDose.getAppliedDose()
                                + " " + NewInsulinDose.getDate()
                                + " " + NewInsulinDose.getTime(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }



    public void submitInsulinDose()
    {
        String stringInsulinType = editTextInsulinType.getText().toString();

        int intInsulinType = -1;

        try {
            intInsulinType = Integer.parseInt(stringInsulinType);
        }
        catch (NumberFormatException e) {

        }

        String stringInsulinDose = editTextInsulinDose.getText().toString();
        float floatInsulinDose = -1;

        try {
            floatInsulinDose = NumberFormat.getInstance().parse(editTextInsulinDose.getText().toString()).floatValue();
        }
        catch (ParseException e) {

        }

        createInsulinDose(intInsulinType, floatInsulinDose);

    }

    public void createInsulinDose(int insulinType, float insulinDose)
    {
        NewInsulinDose.setDate(); //set current date
        NewInsulinDose.setTime(); //set current time
        NewInsulinDose.setInsulinType(insulinType);
        NewInsulinDose.setAppliedDose(insulinDose);
    }
}
