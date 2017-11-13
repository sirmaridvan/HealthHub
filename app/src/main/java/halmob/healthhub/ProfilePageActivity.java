package halmob.healthhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import halmob.healthhub.Models.Person;

public class ProfilePageActivity extends AppCompatActivity {
    private static Person person;
    private TextView name;
    private TextView email;
    private ImageView profilePhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        name  = findViewById(R.id.name);
        email  = findViewById(R.id.email);
        profilePhoto = findViewById(R.id.profile_photo);
        final DatabaseReference personRef = FirebaseUtil.getPeopleRef().child(FirebaseUtil.getCurrentUserId());
        personRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                person = dataSnapshot.getValue(Person.class);
                name.setText(person.getDisplayName());
                email.setText(person.getEmail());
                GlideUtil.loadProfileIcon(person.getPhotoUrl(), profilePhoto);
                personRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
