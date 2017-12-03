package halmob.healthhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import halmob.healthhub.EventListeners.HealthmanListener;
import halmob.healthhub.EventListeners.PeopleListener;
import halmob.healthhub.EventListeners.UserTypeListener;
import halmob.healthhub.Models.Person;

public class UserSearchActivity extends AppCompatActivity implements PeopleListener,UserTypeListener,HealthmanListener{
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        FirebaseTransaction.setUserTypetListenerListener(this);
        FirebaseTransaction.getCurrentUserType();



    }
    @Override
    public void peopleRead(final List<Person> personList) {

        UserList adapter = new
                UserList(UserSearchActivity.this, personList);
        list=findViewById(R.id.listUser);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(UserSearchActivity.this, ProfilePageActivity.class);
                intent.putExtra("userId",personList.get(position).getUid());
                startActivity(intent);
            }
        });

    }
    @Override
    public void usertypeRead(String userType){
        if(userType.equals("Healthman")) {
            FirebaseTransaction.setHealthmanListenerListener(this);
            FirebaseTransaction.getHealthmans();
        }else{
            FirebaseTransaction.setPeopleListenerListener(this);
            FirebaseTransaction.getAllPeople();
        }
    }
    @Override
    public void healthmanRead(final List<Person> personList){
        UserList adapter = new
                UserList(UserSearchActivity.this, personList);
        list=findViewById(R.id.listUser);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(UserSearchActivity.this, ProfilePageActivity.class);
                intent.putExtra("userId",personList.get(position).getUid());
                startActivity(intent);
            }
        });
    }

}
