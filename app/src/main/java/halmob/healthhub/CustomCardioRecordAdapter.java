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

import halmob.healthhub.Models.SportForBodyWork;
import halmob.healthhub.Models.SportForCardio;

public class CustomCardioRecordAdapter extends ArrayAdapter<SportForCardio> {

    CustomCardioRecordAdapter(Context context, List<SportForCardio> cardioList) {
        super(context, R.layout.custom_cardiolist, cardioList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        SportForCardio singleCardioRecordElement = getItem(position);


        TextView textView = (TextView) customView.findViewById(R.id.medInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.pills);

        imageView.setImageResource(R.drawable.cardioicon);
        textView.setText("Exercise -> " + singleCardioRecordElement.getNameOfExerciseForCardio() + "\n" +"\n" +
                 singleCardioRecordElement.getMinuteOfExerciseForCardio() + "Minutes " + "\n" +
                "Burned " + singleCardioRecordElement.getBurnedCaloriesForCardio() +"Calories" + "\n" +
                "Exercise Date " + singleCardioRecordElement.getExerciseDateForCardio() + "\n"
        );
        return customView;
    }
}