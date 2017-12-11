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

import halmob.healthhub.Models.BloodSugar;


/**
 * Created by hakan on 19.11.2017.
 */

public class CustomBloodSugarAdapter extends ArrayAdapter<BloodSugar>{
    CustomBloodSugarAdapter(Context context, List<BloodSugar> bloodSugarList){
        super(context, R.layout.custom_row, bloodSugarList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        BloodSugar singleBloodSugarElement = getItem(position);
        TextView textView = (TextView) customView.findViewById(R.id.medInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.pills);

        imageView.setImageResource(R.drawable.icon_healthhub);
        textView.setText("Date: " + singleBloodSugarElement.getDate() + "\n" +
                "Time: " +singleBloodSugarElement.getTime() + "\n" +
                "Hunger Situation: " +singleBloodSugarElement.getHungerSituation() + "\n" +
                "Blood Sugar: " +singleBloodSugarElement.getBloodSugarValue() + "\n" +
                "Extra Notes: " +singleBloodSugarElement.getExtraNotes());
        return customView;
    }
}
