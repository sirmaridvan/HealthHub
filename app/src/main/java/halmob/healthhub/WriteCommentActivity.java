package halmob.healthhub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;

import halmob.healthhub.Models.Author;
import halmob.healthhub.Models.Comment;
import halmob.healthhub.Models.CurrentDate;
import halmob.healthhub.Models.CurrentTime;

public class WriteCommentActivity extends AppCompatActivity implements View.OnClickListener{
    private Button sendCommentButton;
    private EditText editTextComment;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);
        editTextComment = findViewById(R.id.edittext_comment);

        sendCommentButton = findViewById(R.id.send_comment);
        sendCommentButton.setOnClickListener(this);



        Intent intent = getIntent(); // gets the previously created intent
        userId = intent.getStringExtra("userId");

        if(userId == null){
            userId=FirebaseUtil.getCurrentUserId();
        }

    }
    private void sendComment(){
        final Editable commentText = editTextComment.getText();
        editTextComment.setText("");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            //sıçtın
        }

        Author author = new Author(user.getDisplayName(),
                user.getPhotoUrl().toString(), user.getUid());

        Comment comment = new Comment(author, commentText.toString());
        comment.setDate();
        comment.setTime();
        FirebaseTransaction.addComment(comment,userId);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_comment:
                sendComment();
                break;
        }
    }
}
