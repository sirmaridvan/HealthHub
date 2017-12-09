package halmob.healthhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import halmob.healthhub.EventListeners.CommentListener;
import halmob.healthhub.Models.Comment;

public class ReadCommentActivity extends AppCompatActivity implements CommentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comment);

        FirebaseTransaction.setCommentListenerListener(this);
        FirebaseTransaction.getAllCommentAboutMe();
    }
    @Override
    public void commentRead(List<Comment> commentList){
        ListAdapter commentListAdapter = new CustomReadCommentAdapter(this, commentList);
        ListView listCommentRecord = (ListView) findViewById(R.id.listComment);
        listCommentRecord.setAdapter(commentListAdapter);
    }
}
