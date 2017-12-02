package halmob.healthhub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import halmob.healthhub.EventListeners.AddedImageListener;

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

        // new part
        String uniqueKey = setUniqueKey();
        UploadTask uploadTask = FirebaseStorageUtility.getImagesStoragePersonRef().child(uniqueKey).putBytes(bytePhoto);
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
    public static void loadImage(String url, ImageView imageView, Context context) {
        ColorDrawable cd = new ColorDrawable(ContextCompat.getColor(context, R.color.blue_grey_500));
        Glide.with(context)
                .load(url)
                .placeholder(cd)
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    // new part
    public static String setUniqueKey()
    {
        Date now = new Date();
        String uniqueKey = Long.toString(Long.parseLong(new SimpleDateFormat("ddHHmmss").format(now)));
        uniqueKey = uniqueKey + ".jpg";
        return uniqueKey;
    }
}
