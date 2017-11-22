package halmob.healthhub;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import halmob.healthhub.EventListeners.AddedImageListener;
import halmob.healthhub.EventListeners.DrugListener;
import halmob.healthhub.Models.MedicalAnalysis;

/**
 * Created by RIDVAN SIRMA on 11/22/2017.
 */

public class FirebaseStorageUtility {
    static FirebaseStorage storage = FirebaseStorage.getInstance();
    public static StorageReference getStorageRef(){
        // Create a storage reference from our app
        return storage.getReference();
    }
    public static StorageReference getImagesStorageRef(){
        // Create a child reference
        // imagesRef now points to "images"
        return getStorageRef().child("images");
    }
    public static StorageReference getImagesStoragePersonRef(){
        // Create a child reference
        // imagesRef now points to "images"
        return getImagesStorageRef().child(FirebaseUtil.getCurrentUserId());
    }

    private static AddedImageListener addedImageListener;
    public static void setAddedImageListener(AddedImageListener listen) {
        addedImageListener = listen;
    }
    public static void setReportImage(Bitmap mphoto){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        mphoto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytePhoto = baos.toByteArray();
        //"sample.jpg" yerine her çekilen fotoğraf için bir eşsiz isim oluşturulmalı ve öyle yazılmalı.Bu iş için tarih ve saat kullanılabilir.
        UploadTask uploadTask = FirebaseStorageUtility.getImagesStoragePersonRef().child("sample.jpg").putBytes(bytePhoto);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                addedImageListener.imageAdded(downloadUrl);
            }
        });
    }
}
