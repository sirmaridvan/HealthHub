package halmob.healthhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import halmob.healthhub.Models.Drug;


public class MedicineActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextStartDate;
    private EditText editTextEndDate;
    private EditText editTextHowMany;

    private Button buttonSubmit;

    Drug medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        medicine = new Drug();

        editTextName = (EditText) findViewById(R.id.editText_1);
        editTextStartDate = (EditText) findViewById(R.id.editText_2);
        editTextEndDate = (EditText) findViewById(R.id.editText_3);
        editTextHowMany = (EditText) findViewById(R.id.editText_4);

        buttonSubmit = (Button) findViewById(R.id.button_submit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitMedicine();
                Toast.makeText(getApplicationContext(), "Data: "+ medicine.getName() + " " + medicine.getStartDate(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void submitMedicine()
    {
        String input1 = editTextName.getText().toString();
        String input2 = editTextStartDate.getText().toString();
        String input3 = editTextEndDate.getText().toString();
        String input4 = editTextHowMany.getText().toString();

        createMedicine(input1, input2, input3, input4);

    }

    public void createMedicine(String s1, String s2, String s3, String s4)
    {
        medicine.setName(s1);

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date parsedDate = dateFormat.parse(s2);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            medicine.setStartDate(timestamp.toString());

            parsedDate = dateFormat.parse(s3);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
            medicine.setEndDate(timestamp.toString());
        }
        catch (Exception e){
            // generic exception
        }

        medicine.setHowMany(s4);
    }
}
