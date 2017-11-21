package halmob.healthhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import halmob.healthhub.EventListeners.FoodListener;
import halmob.healthhub.Models.Food;

public class SampleReadActivity extends AppCompatActivity implements FoodListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_read);
        FirebaseTransaction.setFoodListener(this);
        FirebaseTransaction.getFoods();
    }
    @Override
    public void foodRead(List<Food> foods){
        //kullanıcıyla ilgili bütün ilaçlar drugList'in içinde
        //istediğin gibi kullan
        int a=1;
    }
}
