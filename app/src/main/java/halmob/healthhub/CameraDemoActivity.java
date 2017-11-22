package halmob.healthhub;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.List;

import halmob.healthhub.EventListeners.AddedImageListener;
import halmob.healthhub.Models.Drug;
import halmob.healthhub.Models.MedicalAnalysis;

public class CameraDemoActivity extends AppCompatActivity implements AddedImageListener{

    private static final int CAMERA_REQUEST = 1888;
    private ImageView mimageView;
    private Button takePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_demo);
        // Create a storage reference from our app

        mimageView = this.findViewById(R.id.image_from_camera);
        takePhoto = this.findViewById(R.id.take_image_from_camera);
        takePhoto.setEnabled(false);

        if (checkSelfPermission(android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                    111);
        }else{
            takePhoto.setEnabled(true);
        }
        FirebaseStorageUtility.setAddedImageListener(this);
    }

    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    public void getImageFromDatabase(View view){
        String uri = "https://firebasestorage.googleapis.com/v0/b/healthhub-a0d45.appspot.com/o/images%2FnUulYae7H9btz1TrfaxSBlHKsPc2%2Fsample.jpg?alt=media&token=0c90ac19-9056-4722-b50d-f01651f51847";
        FirebaseStorageUtility.loadImage(uri,mimageView,getApplicationContext());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            //mimageView.setImageBitmap(mphoto);
            FirebaseStorageUtility.setReportImage(mphoto);
        }
    }
    @Override
    public void imageAdded(Uri uri){
        MedicalAnalysis medicalAnalysis = new MedicalAnalysis();
        medicalAnalysis.setDate("1111");
        medicalAnalysis.setReportName("222");
        medicalAnalysis.setReportUri(uri.toString());
        FirebaseTransaction.addMedicalAnalysis(medicalAnalysis);
    }
    @Override
    public void  onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 111) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
                takePhoto.setEnabled(true);
            }
            else {
                // Your app will not have this permission. Turn off all functions
                // that require this permission or it will force close like your
                // original question
                super.onBackPressed();
            }
        }
    }

}
