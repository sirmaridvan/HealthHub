package halmob.healthhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import halmob.healthhub.EventListeners.FoodListener;
import halmob.healthhub.Models.Food;

public class SampleReadActivity extends AppCompatActivity implements FoodListener {

    private TextView newTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_read);
        FirebaseTransaction.setFoodListener(this);
        FirebaseTransaction.getFoods();
    }
    @Override
    public void foodRead(List<Food> foods){
        //kullanıcıyla ilgili bütün yemekler foods içinde
        //istediğin gibi kullan

        //aşağıdaki şekilde kullanılabilir, denemek için commentı kaldırıp debugger kullan
        //Food newFood = foods.get(1);
        //String calorie = newFood.getCalorie();
        //int a=1;
    }
}
