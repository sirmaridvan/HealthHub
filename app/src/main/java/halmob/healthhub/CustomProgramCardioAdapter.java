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

import halmob.healthhub.Models.SportForCardio;
import halmob.healthhub.Models.SportProgramForCardio;

/**
 * Created by Furkan Ekici on 8.12.2017.
 */

public class CustomProgramCardioAdapter extends ArrayAdapter<SportProgramForCardio>{
    CustomProgramCardioAdapter(Context context, List<SportProgramForCardio> CardioProgramList) {
        super(context,R.layout.custom_row, CardioProgramList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        SportProgramForCardio singleCardioRecordElement = getItem(position);


        TextView textView = (TextView) customView.findViewById(R.id.medInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.pills);

        imageView.setImageResource(R.drawable.cardioicon);
        textView.setText("Exercise->" + singleCardioRecordElement.getNameOfExerciseForCardio() + "\n" +"\n" +
                singleCardioRecordElement.getMinuteOfExerciseForCardio() + "Minutes" + "\n" +
                "Frequency:" + singleCardioRecordElement.getFrequencyForCardio() + "\n"
        );
        return customView;
    }
}

