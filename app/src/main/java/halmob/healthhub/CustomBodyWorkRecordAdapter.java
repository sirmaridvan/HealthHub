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

public class CustomBodyWorkRecordAdapter extends ArrayAdapter<SportForBodyWork> {

    CustomBodyWorkRecordAdapter(Context context, List<SportForBodyWork> bodyWorkList) {
        super(context, R.layout.custom_row, bodyWorkList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        SportForBodyWork singleBodyWorkRecordElement = getItem(position);


        TextView textView = (TextView) customView.findViewById(R.id.medInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.pills);

        imageView.setImageResource(R.drawable.bodybuildingicon);
        textView.setText("Exercise->  " + singleBodyWorkRecordElement.getNameOfExerciseForBodyWork() + "\n" +
                "Repetition: " + singleBodyWorkRecordElement.getNumberOfRepetitionForBodyWork() + "\n" +
                "Set: " + singleBodyWorkRecordElement.getNumberOfSetForBodyWork() + "\n" +
                "Weight: " + singleBodyWorkRecordElement.getWeightForBodyWork() + "\n" +
                "Date Exercise" + singleBodyWorkRecordElement.getExerciseDateForBodyWork() + "\n"
        );
        return customView;
    }
}


