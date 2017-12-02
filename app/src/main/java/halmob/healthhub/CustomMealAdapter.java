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

import halmob.healthhub.Models.Meal;

/**
 * Created by hakan on 2.12.2017.
 */

public class CustomMealAdapter extends ArrayAdapter<Meal> {
    CustomMealAdapter(Context context, List<Meal> mealList){
        super(context, R.layout.custom_row, mealList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        Meal singleMealElement = getItem(position);
        TextView textView = (TextView) customView.findViewById(R.id.medInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.pills);

        imageView.setImageResource(R.drawable.pills);
        textView.setText("Date: " + singleMealElement.getDate() + "\n" +
                "Time: " + singleMealElement.getTime() + "\n" +
                "Insulin Type: " + singleMealElement.getPortion() + "\n" +
                "Applied Dose: " + singleMealElement.getTotalCarbohydrate() + "\n");
        return customView;
    }
}
