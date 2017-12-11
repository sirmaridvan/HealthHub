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

import halmob.healthhub.Models.InsulinDose;

/**
 * Created by hakan on 22.11.2017.
 */

public class CustomInsulinDoseAdapter extends ArrayAdapter<InsulinDose>{
    CustomInsulinDoseAdapter(Context context, List<InsulinDose> insulinDoseList){
        super(context, R.layout.custom_row, insulinDoseList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        InsulinDose singleInsulinDoseElement = getItem(position);
        TextView textView = (TextView) customView.findViewById(R.id.medInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.pills);

        imageView.setImageResource(R.drawable.add_record_icon);
        textView.setText("Date: " + singleInsulinDoseElement.getDate() + "\n" +
                "Time: " + singleInsulinDoseElement.getTime() + "\n" +
                "Insulin Type: " + singleInsulinDoseElement.getInsulinType() + "\n" +
                "Applied Dose: " + singleInsulinDoseElement.getAppliedDose() + "\n");
        return customView;
    }
}
