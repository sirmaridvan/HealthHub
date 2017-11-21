package halmob.healthhub.Models;

/**
 * Created by hakan on 11.11.2017.
 */

public class Food {
    String foodName;
    float protein;
    float carbohydrate;
    float fat;
    float calorie;

    public String getFoodName() { return foodName;}
    public void setFoodName(String newFoodName) {
        this.foodName = newFoodName;
    }

    public float getProtein() { return protein;}
    public void setProtein(float newProtein) {
        this.protein = newProtein;
    }

    public float getCarbohydrate() {
        return carbohydrate;
    }
    public void setCarbohydrate(float newCarbohydrate) {
        this.carbohydrate = newCarbohydrate;
    }

    public float getFat() {
        return fat;
    }
    public void setFat(float newFat) {
        this.fat = newFat;
    }

    public float getCalorie() {
        return calorie;
    }
    public void setCalorie(float newCalorie) {
        this.calorie = newCalorie;
    }

}
