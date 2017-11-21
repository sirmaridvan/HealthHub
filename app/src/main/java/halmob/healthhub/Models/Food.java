package halmob.healthhub.Models;

/**
 * Created by hakan on 11.11.2017.
 */

public class Food {
    float protein;
    float carbohydrate;
    float fat;
    float calorie;
    float glysemicIndex;
    float carbohydrateCount;

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

    public float getGlysemicIndex() {
        return glysemicIndex;
    }
    public void setGlysemicIndex(float newGlysemicIndex) {
        this.glysemicIndex = newGlysemicIndex;
    }

    public float getCarbohydrateCount() {
        return carbohydrateCount;
    }

    public void setCarbohydrateCount(float newCarbohydrateCount) {
        this.carbohydrateCount = newCarbohydrateCount;
    }
}
