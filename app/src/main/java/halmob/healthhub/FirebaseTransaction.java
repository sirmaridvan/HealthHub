package halmob.healthhub;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import halmob.healthhub.EventListeners.BloodSugarListener;
import halmob.healthhub.EventListeners.BodyWorkListener;
import halmob.healthhub.EventListeners.CardioListener;
import halmob.healthhub.EventListeners.CommentListener;
import halmob.healthhub.EventListeners.DrugListener;
import halmob.healthhub.EventListeners.FoodListener;
import halmob.healthhub.EventListeners.HealthmanListener;
import halmob.healthhub.EventListeners.InsulinDoseListener;
import halmob.healthhub.EventListeners.MealListener;
import halmob.healthhub.EventListeners.PeopleListener;
import halmob.healthhub.EventListeners.ProspectusListener;
import halmob.healthhub.EventListeners.ReportListener;
import halmob.healthhub.EventListeners.StaticBodyWorkListener;
import halmob.healthhub.EventListeners.UserTypeListener;
import halmob.healthhub.Models.BloodSugar;
import halmob.healthhub.Models.Comment;
import halmob.healthhub.Models.Drug;
import halmob.healthhub.Models.Food;
import halmob.healthhub.Models.InsulinDose;
import halmob.healthhub.Models.Meal;
import halmob.healthhub.Models.MedicalAnalysis;
import halmob.healthhub.Models.Person;
import halmob.healthhub.Models.ProspectusInfo;
import halmob.healthhub.Models.Report;
import halmob.healthhub.Models.SportForBodyWork;
import halmob.healthhub.Models.SportForCardio;
import halmob.healthhub.Models.StaticExerciseForBodyWork;

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

    public static void addComment(Comment comment,String userId){
        FirebaseUtil.getPeopleRef().child(userId).child("comments").push().setValue(comment, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference firebase) {
                if (error != null) {
                }
            }
        });
    }

    public static void addMedicalAnalysis(MedicalAnalysis medicalAnalysis){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("medicalAnalysis").push().setValue(medicalAnalysis, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference firebase) {
                if (error != null) {
                }
            }
        });
    }

    public static void addProspectusInfo(ProspectusInfo prospectusInfo){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("prospectusInfo").push().setValue(prospectusInfo, new DatabaseReference.CompletionListener() {
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
    public static void getDrugs(String userId){
        final List<Drug> drugList = new ArrayList<Drug>();
        FirebaseUtil.getPeopleRef().child(userId).child("drugs").addListenerForSingleValueEvent(new ValueEventListener() {
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

    private static ProspectusListener mProspectusListener;

    public static void setProspectusListenerListener(ProspectusListener listen) {
        mProspectusListener = listen;
    }
    public static void getProspectuses(){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        final List<ProspectusInfo> prospectusInfoList = new ArrayList<ProspectusInfo>();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("prospectusInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ProspectusInfo prospectus = postSnapshot.getValue(ProspectusInfo.class);
                    prospectusInfoList.add(prospectus);
                }
                if (mProspectusListener != null) {
                    mProspectusListener.prospectusRead(prospectusInfoList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }

    //RIDVAN TARAFINDAN KONTROL EDİLECEK BURADAN BAŞLANARAK
    //RIDVAN: KİM DEMİŞ? :D
    //GÖRKEM: SELAM BEYLER, NASIL GİDİYOR?

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
    public static void getBloodSugars(String userId){
        final List<BloodSugar> bloodSugarList = new ArrayList<BloodSugar>();
        FirebaseUtil.getPeopleRef().child(userId).child("bloodSugars").addListenerForSingleValueEvent(new ValueEventListener() {
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


    public static void addInsulinDose(InsulinDose newInsulinDose){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("insulinDoses").push().setValue(newInsulinDose, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference firebase) {
                if (error != null) {
                }
            }
        });
    }
    private static InsulinDoseListener mInsulinDoseListener;

    public static void setmInsulinDoseListener(InsulinDoseListener listen) {
        mInsulinDoseListener = listen;
    }
    public static void getInsulinDoses(String userId){
        //final String userId = FirebaseUtil.getCurrentUserId();
        final List<InsulinDose> insulinDoseList = new ArrayList<InsulinDose>();
        FirebaseUtil.getPeopleRef().child(userId).child("insulinDoses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    InsulinDose insulinDose = postSnapshot.getValue(InsulinDose.class);
                    insulinDoseList.add(insulinDose);
                }
                if (mInsulinDoseListener != null) {
                    mInsulinDoseListener.insulinDosesRead(insulinDoseList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }

///////////////////////////////////////////////////////////////////////////
    //FOR BODYWORK

    public static void addBodyWork(SportForBodyWork newBodyWork){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("bodyWork").push().setValue(newBodyWork, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference firebase) {
                if (error != null) {
                }
            }
        });
    }
    private static BodyWorkListener mBodyWorkListener;

    public static void setBodyWorkListenerListener(BodyWorkListener listen) {
        mBodyWorkListener = listen;
    }
    public static void getBodyWork(String userId){
        final List<SportForBodyWork> BodyWorkList = new ArrayList<SportForBodyWork>();
        FirebaseUtil.getPeopleRef().child(userId).child("bodyWork").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    SportForBodyWork BodyWork = postSnapshot.getValue(SportForBodyWork.class);
                    BodyWorkList.add(BodyWork);
                }
                if (mBodyWorkListener != null) {
                    mBodyWorkListener.BodyWorkRead(BodyWorkList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }
///////////////////////////////////////////////////////////////////
    //FOR CARDIO

    public static void addCardio(SportForCardio Cardio){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("cardio").push().setValue(Cardio, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference firebase) {
                if (error != null) {
                }
            }
        });
    }
    private static CardioListener mCardioListener;
    public static void setCardioListenerListener(CardioListener listen) {
        mCardioListener = listen;
    }
    public static void getCardio(String userId){
        final List<SportForCardio> CardioList = new ArrayList<>();
        FirebaseUtil.getPeopleRef().child(userId).child("cardio").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    SportForCardio Cardio = postSnapshot.getValue(SportForCardio.class);
                    CardioList.add(Cardio);
                }
                if (mCardioListener != null) {
                    mCardioListener.CardioRead(CardioList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }

//////////////////////////////////////////////////////////////////////
    private static PeopleListener mPeopleListener;

    public static void setPeopleListenerListener(PeopleListener listen) {
        mPeopleListener = listen;
    }
    public static void getAllPeople(){
        final List<Person> personList = new ArrayList<Person>();
        FirebaseUtil.getPeopleRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Person person = postSnapshot.getValue(Person.class);
                    person.setUid(postSnapshot.getKey());
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

    private static HealthmanListener mHealthmanListener;

    public static void setHealthmanListenerListener(HealthmanListener listen) {
        mHealthmanListener = listen;
    }
    public static void getHealthmans(){
        final List<Person> personList = new ArrayList<Person>();
        FirebaseUtil.getPeopleRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Person person = postSnapshot.getValue(Person.class);
                    person.setUid(postSnapshot.getKey());
                    if(person.getUserType().equals("Healthman"))
                        personList.add(person);
                }
                if (mHealthmanListener != null) {
                    mHealthmanListener.healthmanRead(personList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }

    private static StaticBodyWorkListener mStaticBodyWorkListener;

    public static void setStaticBodyWorkListener(StaticBodyWorkListener listen){mStaticBodyWorkListener = listen; }
    public static void getStaticBodyWork(){
        final List<StaticExerciseForBodyWork> StaticExerciseForBodyWorkList = new ArrayList<StaticExerciseForBodyWork>();
        FirebaseUtil.getStaticBodyWork().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    StaticExerciseForBodyWork staticExerciseForBodyWork = postSnapshot.getValue(StaticExerciseForBodyWork.class);
                    StaticExerciseForBodyWorkList.add(staticExerciseForBodyWork);
                }
                if(mStaticBodyWorkListener !=null){
                    mStaticBodyWorkListener.StaticBodyWorkRead(StaticExerciseForBodyWorkList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private static FoodListener mFoodListener;

    public static void setFoodListener(FoodListener listen) {
        mFoodListener = listen;
    }
    public static void getFoods(){
        final List<Food> foodList = new ArrayList<Food>();
        FirebaseUtil.getFoodRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Food food = postSnapshot.getValue(Food.class);
                    foodList.add(food);
                }
                if (mFoodListener != null) {
                    mFoodListener.foodRead(foodList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }


    // AddMeal part is added below

    public static void addMeal(Meal newMeal){
        final String currentUserId = FirebaseUtil.getCurrentUserId();
        FirebaseUtil.getPeopleRef().child(currentUserId).child("meal").push().setValue(newMeal, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference firebase) {
                if (error != null) {
                }
            }
        });
    }

    private static MealListener mMealListener;

    public static void setmMealListener(MealListener listen) {
        mMealListener = listen;
    }
    public static void getMeals(String userId){
        //final String userId = FirebaseUtil.getCurrentUserId();
        final List<Meal> mealList = new ArrayList<Meal>();
        FirebaseUtil.getPeopleRef().child(userId).child("meal").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Meal meal = postSnapshot.getValue(Meal.class);
                    mealList.add(meal);
                }
                if (mMealListener != null) {
                    mMealListener.mealsRead(mealList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }



    private static ReportListener mReportListener;

    public static void setReportListenerListener(ReportListener listen) {
        mReportListener = listen;
    }

    public static void getReports(String userId){
        final List<Report> reportList = new ArrayList<Report>();
        FirebaseUtil.getPeopleRef().child(userId).child("medicalAnalysis").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot reportSnapshot: dataSnapshot.getChildren()) {
                    Report report = reportSnapshot.getValue(Report.class);
                    reportList.add(report);
                }
                if (mReportListener != null) {
                    mReportListener.reportRead(reportList);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }

    private static UserTypeListener mUserTypeListener;

    public static void setUserTypetListenerListener(UserTypeListener listen) {
        mUserTypeListener = listen;
    }
    public static void getCurrentUserType() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userType;
        if (user != null) {
            final DatabaseReference personRef = FirebaseUtil.getPeopleRef().child(user.getUid());
            personRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Person person = dataSnapshot.getValue(Person.class);
                    mUserTypeListener.usertypeRead(person.getUserType());
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }
    private static CommentListener commentListener;

    public static void setCommentListenerListener(CommentListener listen) {
        commentListener = listen;
    }
    public static void getAllCommentAboutMe() {
        final List<Comment> commentList = new ArrayList<Comment>();
        final String userId = FirebaseUtil.getCurrentUserId();
        final DatabaseReference commentRef = FirebaseUtil.getPeopleRef().child(userId).child("comments");
        commentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Comment comment = dataSnapshot.getValue(Comment.class);
                if(commentListener != null) {
                    commentListener.commentRead(commentList);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
