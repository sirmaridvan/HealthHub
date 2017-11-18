package halmob.healthhub;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import halmob.healthhub.EventListeners.BloodSugarListener;
import halmob.healthhub.EventListeners.DrugListener;
import halmob.healthhub.EventListeners.PeopleListener;
import halmob.healthhub.Models.BloodSugar;
import halmob.healthhub.Models.Drug;
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
    public static void addDrug(Drug drug){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("drugs").push().setValue(drug, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference firebase) {
                if (error != null) {
                }
            }
        });
    }
    private static DrugListener mDrugListener;

    public static void setDrugListenerListener(DrugListener listen) {
        mDrugListener = listen;
    }
    public static void getDrugs(){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        final List<Drug> drugList = new ArrayList<Drug>();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("drugs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Drug drug = postSnapshot.getValue(Drug.class);
                    drugList.add(drug);
                }
                if (mDrugListener != null) {
                    mDrugListener.drugsRead(drugList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }






    //RIDVAN TARAFINDAN KONTROL EDİLECEK BURADAN BAŞLANARAK

    public static void addBloodSugar(BloodSugar newBloodSugar){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("bloodSugars").push().setValue(newBloodSugar, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference firebase) {
                if (error != null) {
                }
            }
        });
    }
    private static BloodSugarListener mBloodSugarListener;

    public static void setmBloodSugarListener(BloodSugarListener listen) {
        mBloodSugarListener = listen;
    }
    public static void getBloodSugars(){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        final List<BloodSugar> bloodSugarList = new ArrayList<BloodSugar>();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("bloodSugars").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    BloodSugar bloodSugar = postSnapshot.getValue(BloodSugar.class);
                    bloodSugarList.add(bloodSugar);
                }
                if (mBloodSugarListener != null) {
                    mBloodSugarListener.bloodSugarsRead(bloodSugarList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }

    //BURAYA KADAR





    private static PeopleListener mPeopleListener;

    public static void setPeopleListenerListener(PeopleListener listen) {
        mPeopleListener = listen;
    }
    public static void getPeople(){
        final List<Person> personList = new ArrayList<Person>();
        FirebaseUtil.getPeopleRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Person person = postSnapshot.getValue(Person.class);
                    personList.add(person);
                }
                if (mPeopleListener != null) {
                    mPeopleListener.peopleRead(personList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }
}
