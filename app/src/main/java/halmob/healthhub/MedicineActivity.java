package halmob.healthhub;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import halmob.healthhub.EventListeners.DrugListener;
import halmob.healthhub.Models.Drug;


public class MedicineActivity extends AppCompatActivity implements DrugListener {

    private EditText editTextName;
    private EditText editTextHowMany;

    private TextView editTextTime;
    private TextView editTextStartDate;
    private TextView editTextEndDate;

    private TimePickerDialog.OnTimeSetListener timeSetListener1;
    private DatePickerDialog.OnDateSetListener dateSetListener1;
    private DatePickerDialog.OnDateSetListener dateSetListener2;


    private Button buttonSubmit;

    Drug medicine;

    public MedicineActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        medicine = new Drug();

        editTextName = (EditText) findViewById(R.id.editText_1);
        editTextHowMany = (EditText) findViewById(R.id.editText_4);
        editTextTime = (TextView) findViewById(R.id.time);
        editTextStartDate = (TextView) findViewById(R.id.date1);
        editTextEndDate = (TextView) findViewById(R.id.date2);

        editTextTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Calendar timeCal = Calendar.getInstance();
                int hour = timeCal.get(Calendar.HOUR_OF_DAY);
                int minute = timeCal.get(Calendar.MINUTE);

                TimePickerDialog dialog3 = new TimePickerDialog(MedicineActivity.this,
                        TimePickerDialog.THEME_HOLO_LIGHT,
                        timeSetListener1,
                        hour,
                        minute,
                        true
                );
                dialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog3.show();
            }
        });

        editTextStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar cal1 = Calendar.getInstance();
                int year = cal1.get(Calendar.YEAR);
                int month = cal1.get(Calendar.MONTH);
                int day = cal1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog(MedicineActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener1,
                        year, month, day
                );
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();

            }
        });

        editTextEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar cal2 = Calendar.getInstance();
                int year = cal2.get(Calendar.YEAR);
                int month = cal2.get(Calendar.MONTH);
                int day = cal2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog2 = new DatePickerDialog(MedicineActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener2,
                        year, month, day
                );
                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog2.show();

            }
        });

        timeSetListener1 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String time;
                if(minute < 10){
                    time = hour + ":0" + minute;
                }
                else{
                    time = hour + ":" + minute;
                }
                editTextTime.setText(time);
            }
        };

        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;  // since index starts from 0
                String date1 = year + "-" + month + "-" + day;
                editTextStartDate.setText(date1);
            }
        };

        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;  // since index starts from 0
                String date2 = year + "-" + month + "-" + day;
                editTextEndDate.setText(date2);
            }
        };

        buttonSubmit = (Button) findViewById(R.id.button_submit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitMedicine();
                FirebaseTransaction.addDrug(medicine);

                Toast.makeText(getApplicationContext(),
                        "Medicine Record is saved successfully!",
                        Toast.LENGTH_LONG).show();

                Intent intent1 = new Intent(MedicineActivity.this, MedicineMainActivity.class);
                startActivity(intent1);
                finish();

            }
        });
    }

    //kullanıcının ilaçlarını okumak için aşağıdaki iki satırlık kodu kullan. Sonuçlar drugsRead fonksiyonuna düşecek.
        /*FirebaseTransaction.setDrugListenerListener(this);
        FirebaseTransaction.getDrugs();*/

    @Override
    public void drugsRead(List<Drug> drugList){
        //kullanıcıyla ilgili bütün ilaçlar drugList'in içinde
        //istediğin gibi kullan
    }

    public void submitMedicine() {
        String input1 = editTextName.getText().toString();
        String input2 = editTextStartDate.getText().toString();
        String input3 = editTextEndDate.getText().toString();
        String input4 = editTextHowMany.getText().toString();
        String input5 = editTextTime.getText().toString();

        createMedicine(input1, input2, input3, input4, input5);

    }

    public void createMedicine(String s1, String s2, String s3, String s4, String s5) {
        medicine.setName(s1);

        medicine.setStartDate(s2);
        medicine.setEndDate(s3);

        /*
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date parsedDate = dateFormat.parse(s2);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            medicine.setStartDate(timestamp.toString());

            parsedDate = dateFormat.parse(s3);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
            medicine.setEndDate(timestamp.toString());
        } catch (Exception e) {
            // generic exception
        }
        */

        medicine.setHowMany(s4);
        medicine.setTime(s5);
    }
}