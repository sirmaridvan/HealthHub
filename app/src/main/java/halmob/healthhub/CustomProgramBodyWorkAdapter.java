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

import halmob.healthhub.Models.SportProgramForBodyWork;

/**
 * Created by Furkan Ekici on 8.12.2017.
 */

public class CustomProgramBodyWorkAdapter extends ArrayAdapter<SportProgramForBodyWork> {
    CustomProgramBodyWorkAdapter(Context context, List<SportProgramForBodyWork> BodyWorkProgramList) {
        super(context,R.layout.custom_row, BodyWorkProgramList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        SportProgramForBodyWork singleCardioRecordElement = getItem(position);


        TextView textView = (TextView) customView.findViewById(R.id.medInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.pills);

        imageView.setImageResource(R.drawable.bodybuildingicon);
        textView.setText("Exercise-> " + singleCardioRecordElement.getNameOfExerciseForBodyWork() + "\n" +"\n" +
                "Repetition:" + singleCardioRecordElement.getNumberOfRepetitionForBodyWork()  + "\n" +
                "Set:" + singleCardioRecordElement.getNumberOfSetForBodyWork() + "\n" +
                "Frequency:" + singleCardioRecordElement.getFrequencyForBodyWork() + "\n"
        );
        return customView;
    }
}
