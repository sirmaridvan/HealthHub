package halmob.healthhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import halmob.healthhub.Models.Person;

public class ProfilePageActivity extends AppCompatActivity implements View.OnClickListener{
    private static Person person;
    private TextView name;
    private TextView email;
    private Button buttonComment;
    private Button buttonDetail;
    private ImageView profilePhoto;
    private Intent intent;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        name  = findViewById(R.id.name);
        email  = findViewById(R.id.email);
        profilePhoto = findViewById(R.id.profile_photo);
        // getIntent() is a method from the started activity
        Intent intent = getIntent(); // gets the previously created intent
        userId = intent.getStringExtra("userId");

        /*  userId kimin profil sayfasında olduğun bilgisini tutar.
            Asıl kullanıcın id'si için 'FirebaseUtil.getCurrentUserId()' fonksiyonu çağrılabilir.
            Asıl kullanıcı sayfasında bulunduğu kişiyi takip etmek isterse, takip etme fonksiyonu 'FirebaseTransaction.follow(userId);'
            komutunu içermelidir. Bu komut takip etme sistemi için yeterlidir.
         */

        buttonComment = findViewById(R.id.button_comment);
        buttonComment.setOnClickListener(this);

        buttonDetail = findViewById(R.id.buton_detail);
        buttonDetail.setOnClickListener(this);


        final DatabaseReference personRef = FirebaseUtil.getPeopleRef().child(userId);
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
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buton_detail:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
                break;
            case R.id.button_comment:
                //comment activity oluşturulacak
                if(FirebaseUtil.userType.equals("Supervisor")) {
                    intent = new Intent(this, WriteCommentActivity.class);
                    intent.putExtra("userId",userId);
                    startActivity(intent);
                }else if(FirebaseUtil.userType.equals("Healthman")){
                    intent = new Intent(this, ReadCommentActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
