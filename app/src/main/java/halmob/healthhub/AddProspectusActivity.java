package halmob.healthhub;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import halmob.healthhub.EventListeners.AddedImageListener;
import halmob.healthhub.Models.ProspectusInfo;

public class AddProspectusActivity extends AppCompatActivity implements AddedImageListener {
    private EditText editTextName;
    private Button submitProspectusButton;
    private ImageButton takePicButton;

    private static final int CAMERA_REQUEST = 1888;
    private boolean photoCheck = false;
    ProspectusInfo prospectusInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prospectus);

        editTextName = (EditText) findViewById(R.id.medProspectusName1);
        takePicButton = (ImageButton) findViewById(R.id.takePicButton1);
        submitProspectusButton = (Button) findViewById(R.id.buttonSubmitProspectus);

        prospectusInfo = new ProspectusInfo();

        // camera permissions enabled??
        takePicButton.setEnabled(false);
        // disable until image has been added successfully
        submitProspectusButton.setEnabled(false);

        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 111);
        }
        else{
            takePicButton.setEnabled(true);
        }
        FirebaseStorageUtility.setAddedImageListener(this);


        submitProspectusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitRecordData();
            }
        });

        takePicButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                takePic();
            }
        });



    }

    public void takePic()
    {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public void returnToPage()
    {
        Toast.makeText(getApplicationContext(),
                "Prospectus Repord is saved successfully!",
                Toast.LENGTH_LONG).show();

        Intent intent1 = new Intent(AddProspectusActivity.this, MedicineMainActivity.class);
        startActivity(intent1);
        finish();
    }

    public void submitRecordData()
    {
        String input1 = editTextName.getText().toString();

        createProspectusRecord(input1);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            FirebaseStorageUtility.setReportImage(mphoto);
            photoCheck = true;

        }
    }

    @Override
    public void imageAdded(Uri uri) {
        prospectusInfo.setProspectusUri(uri.toString());
        photoCheck = true;
        submitProspectusButton.setEnabled(true);
        // FirebaseTransaction.addMedicalAnalysis(medAnalysis);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 111) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
                takePicButton.setEnabled(true);
            }
            else {
                // Your app will not have this permission. Turn off all functions
                // that require this permission or it will force close like your
                // original question
                super.onBackPressed();
            }
        }
    }


    public void createProspectusRecord(String s1)
    {
        if(validateForm(s1) == true)
        {
            prospectusInfo.setMedName(s1);


            FirebaseTransaction.addProspectusInfo(prospectusInfo);
            returnToPage();
        }

    }


    // s1: name
    public boolean validateForm(String s1)
    {
        boolean flag = true;    // if true form is valid, else form inputs are not valid

        if(s1 == null || s1.trim().isEmpty()){   // if prospectus name is not entered.
            flag = false;
            editTextName.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Medicine Name cannot be left blank!",
                    Toast.LENGTH_LONG).show();

        }


        else if(photoCheck == false){
            flag = false;
            takePicButton.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "You need to take the photo of the medicine prospectus!",
                    Toast.LENGTH_LONG).show();
        }

        return flag;


    }


}
