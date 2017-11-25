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

import java.util.ArrayList;
import java.util.List;

import halmob.healthhub.Models.Drug;

/**
 * Created by gorkem on 18.11.2017.
 */

public class CustomMedAdapter extends ArrayAdapter<Drug> {

    CustomMedAdapter(Context context, List<Drug> medList){
        super(context, R.layout.custom_row, medList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        Drug singleDrugElement = getItem(position);
        
        List<String> timeListArray = new ArrayList<>();
        timeListArray = singleDrugElement.getTimeList();

        String timeListStr = "Time";

        if(timeListArray.size() == 1){
            timeListStr = timeListStr + ": ";
        }
        else{
            timeListStr = timeListStr + "s: ";
        }

        for(int i = 0; i < timeListArray.size(); i++){
            timeListStr = timeListStr + timeListArray.get(i).toString();
            if(i + 1 != timeListArray.size() && timeListArray.size() != 1){
                timeListStr = timeListStr   + ", ";
            }
        }

        TextView textView = (TextView) customView.findViewById(R.id.medInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.pills);

        imageView.setImageResource(R.drawable.pills);
        textView.setText("Name: " + singleDrugElement.getName() + "\n" +
                         "How Many: " + singleDrugElement.getHowMany() + "\n" +
                         "Start Date: " + singleDrugElement.getStartDate() + "\n" +
                         "End Date: " + singleDrugElement.getEndDate() + "\n" +
                         timeListStr);
        return customView;
    }
}
