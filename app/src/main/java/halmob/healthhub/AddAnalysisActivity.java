package halmob.healthhub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import halmob.healthhub.EventListeners.AddedImageListener;
import halmob.healthhub.Models.MedicalAnalysis;

public class AddAnalysisActivity extends AppCompatActivity implements AddedImageListener {

    private EditText editTextName;
    private EditText editTextNote;
    private TextView textViewDate;
    private Button takePictureAndSubmitButton;
    private static final int CAMERA_REQUEST = 1888;

    private DatePickerDialog.OnDateSetListener dateSetListener1;
    MedicalAnalysis medAnalysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_analysis);

        medAnalysis = new MedicalAnalysis();
        editTextName = (EditText) findViewById(R.id.medAnalysisName1);
        editTextNote = (EditText) findViewById(R.id.medAnalysisNotes1);
        textViewDate = (TextView) findViewById(R.id.medAnalysisDate1);
        takePictureAndSubmitButton = (Button) findViewById(R.id.buttonSubmitAnalysis);


        // camera permissions enabled??
        takePictureAndSubmitButton.setEnabled(false);

        if (checkSelfPermission(android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                    111);
        }else{
            takePictureAndSubmitButton.setEnabled(true);
        }
        FirebaseStorageUtility.setAddedImageListener(this);



        textViewDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar cal1 = Calendar.getInstance();
                int year = cal1.get(Calendar.YEAR);
                int month = cal1.get(Calendar.MONTH);
                int day = cal1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog(AddAnalysisActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener1,
                        year, month, day
                );
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();

            }
        });

        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;  // since index starts from 0

                String strMonth = String.valueOf(month);
                String strDay = String.valueOf(day);

                if(month < 10){
                    strMonth = "0" + String.valueOf(month);
                }
                if(day < 10){
                    strDay = "0" + String.valueOf(day);
                }

                String date1 = year + "-" + strMonth + "-" + strDay;
                textViewDate.setText(date1);
            }
        };

        takePictureAndSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicAndSubmitRecord();

            }
        });

    }


    public void takePicAndSubmitRecord()
    {
        String input1 = editTextName.getText().toString();
        String input2 = editTextNote.getText().toString();
        String input3 = textViewDate.getText().toString();

        createMedicalAnalysis(input1, input3, input2);

        // new camera codes
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);


        Toast.makeText(getApplicationContext(),
                "Medical Analysis Repord is saved successfully!",
                Toast.LENGTH_LONG).show();

        Intent intent1 = new Intent(AddAnalysisActivity.this, MedicalAnalysisActivity.class);
        startActivity(intent1);
        finish();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            //mimageView.setImageBitmap(mphoto);
            FirebaseStorageUtility.setReportImage(mphoto);
        }
    }

    @Override
    public void imageAdded(Uri uri) {
        medAnalysis.setReportUri(uri.toString());
        FirebaseTransaction.addMedicalAnalysis(medAnalysis);
    }


    @Override
    public void  onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 111) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
                takePictureAndSubmitButton.setEnabled(true);
            }
            else {
                // Your app will not have this permission. Turn off all functions
                // that require this permission or it will force close like your
                // original question
                super.onBackPressed();
            }
        }
    }

    public void createMedicalAnalysis(String s1, String s2, String s3)
    {
        if(validateForm(s1, s2, s3) == true)
        {
            medAnalysis.setReportName(s1);
            medAnalysis.setDate(s2);
            medAnalysis.setReportNotes(s3);

        }
    }

    // s1: name, s2: notes, s3: date
    public boolean validateForm(String s1, String s2, String s3)
    {
        boolean flag = true;    // if true form is valid, else form inputs are not valid

        // get today's date to check
        String strToday;
        Calendar cToday = Calendar.getInstance();
        int year1 = cToday.get(Calendar.YEAR);
        int month1 = cToday.get(Calendar.MONTH) + 1;
        int day1 = cToday.get(Calendar.DAY_OF_MONTH);

        if(month1 < 10 && day1 < 10){
            strToday = String.valueOf(year1) + "-0" + String.valueOf(month1) + "-0" + String.valueOf(day1);
        }
        else if(month1 < 10){
            strToday = String.valueOf(year1) + "-0" + String.valueOf(month1) + "-" + String.valueOf(day1);
        }
        else if(day1 < 10){
            strToday = String.valueOf(year1) + "-" + String.valueOf(month1) + "-0" + String.valueOf(day1);
        }
        else{
            strToday = String.valueOf(year1) + "-" + String.valueOf(month1) + "-" + String.valueOf(day1);
        }

        if(s1 == null || s1.trim().isEmpty()){   // if analysis name is not entered.
            flag = false;
            editTextName.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Analysis Name cannot be left blank!",
                    Toast.LENGTH_LONG).show();

        }

        else if(s3 == null || s2.trim().isEmpty()){
            flag = false;
            textViewDate.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Report date cannot be left blank!",
                    Toast.LENGTH_LONG).show();
        }

        else if(s3 == null || s3.trim().isEmpty()){   // if analysis note is not entered.
            flag = false;
            editTextNote.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Analysis Notes cannot be left blank!",
                    Toast.LENGTH_LONG).show();

        }

        return flag;


    }





}
