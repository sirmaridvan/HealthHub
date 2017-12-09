package halmob.healthhub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import halmob.healthhub.Models.Author;
import halmob.healthhub.Models.Comment;

/**
 * Created by Furkan Ekici on 9.12.2017.
 */

public class CustomReadCommentAdapter extends ArrayAdapter<Comment> {
    CustomReadCommentAdapter(Context context, List<Comment> CommentList) {
        super(context,R.layout.custom_row, CommentList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        Comment singleCardioRecordElement = getItem(position);
        Author author = singleCardioRecordElement.getAuthor();

        TextView textView = (TextView) customView.findViewById(R.id.medInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.pills);

        FirebaseStorageUtility.loadImage(author.getProfile_picture(),imageView, this.getContext());

        textView.setText(
                singleCardioRecordElement.getText() + "\n" + "\n" +
                author.getFull_name() + " commented by at"+ "\n" +
                "date:"+singleCardioRecordElement.getDate()+ "\n" +
                "time:" + singleCardioRecordElement.getTime()
        );
        return customView;
    }
}
