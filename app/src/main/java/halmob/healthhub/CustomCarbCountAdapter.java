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

public class CustomCarbCountAdapter extends ArrayAdapter<Meal> {
    CustomCarbCountAdapter(Context context, List<Meal> mealList){
        super(context, R.layout.custom_row_meal, mealList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row_meal, parent, false);

        Meal singleMealElement = getItem(position);
        TextView textView = (TextView) customView.findViewById(R.id.medInfo);
        TextView textViewDate = (TextView) customView.findViewById(R.id.textView3);
        TextView textViewTime = (TextView) customView.findViewById(R.id.textView4);
        ImageView imageView = (ImageView) customView.findViewById(R.id.pills);

        if (singleMealElement.getFoodType().equals("Milk Product")) {
            if (singleMealElement.getFoodName().equals("Milk"))
                imageView.setImageResource(R.drawable.milk);
            else if (singleMealElement.getFoodName().equals("Cheese"))
                imageView.setImageResource(R.drawable.cheese);
        }
        else if (singleMealElement.getFoodType().equals("Fruit")) {
            if (singleMealElement.getFoodName().equals("Watermelon")) ;
                imageView.setImageResource(R.drawable.watermelon);
        }
        else if (singleMealElement.getFoodType().equals("Meat and Meat Products")) {
            if( singleMealElement.getFoodName().equals("Steak"))
                imageView.setImageResource(R.drawable.steak);
        }
        else if (singleMealElement.getFoodType().equals("Dessert")) {
            if( singleMealElement.getFoodName().equals("Cheesecake") )
                imageView.setImageResource(R.drawable.cheesecake);
        }
        else if (singleMealElement.getFoodType().equals("Vegetable")) {
            if( singleMealElement.getFoodName().equals("Cauliflower") )
                imageView.setImageResource(R.mipmap.cauliflower);
        }
        else if (singleMealElement.getFoodType().equals("Pasta")) {
            if( singleMealElement.getFoodName().equals("Penne") )
                imageView.setImageResource(R.drawable.pasta_penne);
        }
        else if (singleMealElement.getFoodType().equals("Rice")) {
            if( singleMealElement.getFoodName().equals("Pilaf with Rice") )
                imageView.setImageResource(R.drawable.rice);
        }

        textViewDate.setText(singleMealElement.getDate());
        textViewTime.setText(singleMealElement.getTime());

        textView.setText("Date: " + singleMealElement.getDate() + "\n" +
                "Time: " + singleMealElement.getTime() + "\n" +
                "Food Name: " + singleMealElement.getFoodName() + "\n" +
                "Food Type: " + singleMealElement.getFoodType() + "\n" +
                "Unit Portion Size: " + singleMealElement.getPortionSize() + "\n" +
                "Your Portion Size: " + singleMealElement.getFloatPortionSize() + "\n" +
                "Carbohydrate Count: " + singleMealElement.getTotalCarbohydrateCount());
        return customView;
    }
}
