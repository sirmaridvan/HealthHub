package halmob.healthhub;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import halmob.healthhub.Models.Person;

/**
 * Created by RIDVAN SIRMA on 11/15/2017.
 */

public class FirebaseTransaction {
    public static void  addUser(Person user) {
        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("displayName", user.getDisplayName() != null ? user.getDisplayName() : "Anonymous");
        updateValues.put("photoUrl", user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : null);
        updateValues.put("email", user.getEmail() != null ? user.getEmail().toString() : null);
        updateValues.put("userType", user.getUserType());
        FirebaseUtil.getPeopleRef().child(user.getUid()).updateChildren(
                updateValues,
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference databaseReference) {
                        if (firebaseError != null) {
                            /*Toast.makeText(LoginActivity.this,
                                    "Couldn't save user data: " + firebaseError.getMessage(),
                                    Toast.LENGTH_LONG).show();*/
                        }
                    }
                });
    }
    private static void addHealthman(Person user){

    }
    private static void addProfession(Person user){
        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("displayName", user.getDisplayName() != null ? user.getDisplayName() : "Anonymous");
        updateValues.put("photoUrl", user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : null);
        updateValues.put("email", user.getEmail() != null ? user.getEmail().toString() : null);
        FirebaseUtil.getProfessionRef().child(user.getUid()).updateChildren(
                updateValues,
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference databaseReference) {
                        if (firebaseError != null) {
                            /*Toast.makeText(LoginActivity.this,
                                    "Couldn't save user data: " + firebaseError.getMessage(),
                                    Toast.LENGTH_LONG).show();*/
                        }
                    }
                });
    }
    public static void follow( final String followed){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        if (currentUserId == null) {
            //sıçtın
            return;
        }
        // TODO: Convert these to actually not be single value, for live updating when
        // current user follows.
        FirebaseUtil.getPeopleRef().child(currentUserId).child("following").child(followed).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> updatedUserData = new HashMap<>();
                updatedUserData.put("people/" + currentUserId + "/following/" + followed, true);
                updatedUserData.put("people/" + followed + "/followers/" + currentUserId, true);
                FirebaseUtil.getBaseRef().updateChildren(updatedUserData, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                        if (firebaseError != null) {
                            //sıçtın
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }
}
