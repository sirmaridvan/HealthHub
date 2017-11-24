package halmob.healthhub.Models;

/**
 * Created by hakan on 11.11.2017.
 */

public class Food {
    private String foodName;
    private String foodType;
    private String portionSize;
    private String gram;
    private String protein;
    private String carbohydrate;
    private String fat;
    private String calorie;
    private String fiber;
    private String carbohydrateCount;
    private String glysemicIndex;


    public String getFoodName() { return foodName;}
    public void setFoodName(String newFoodName) {
        this.foodName = newFoodName;
    }

    public String getFoodType() {
        return foodType;
    }
    public void setFoodType(String newFoodType) {
        this.foodType = newFoodType;
    }

    public String getProtein() { return protein;}
    public void setProtein(String newProtein) {
        this.protein = newProtein;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }
    public void setCarbohydrate(String newCarbohydrate) {
        this.carbohydrate = newCarbohydrate;
    }

    public String getFat() {
        return fat;
    }
    public void setFat(String newFat) {
        this.fat = newFat;
    }

    public String getCalorie() {
        return calorie;
    }
    public void setCalorie(String newCalorie) {
        this.calorie = newCalorie;
    }

    public String getGram() {
        return gram;
    }
    public void setGram(String newGram) { this.gram = newGram; }

    public String getPortionSize() {
        return portionSize;
    }
    public void setPortionSize(String newPortionSize) {
        this.portionSize = newPortionSize;
    }

    public String getFiber() { return fiber; }
    public void setFiber(String newFiber) {
        this.fiber = newFiber;
    }

    public String getCarbohydrateCount() {
        return carbohydrateCount;
    }
    public void setCarbohydrateCount(String newCarbohydrateCount) { this.carbohydrateCount = newCarbohydrateCount; }

    public String getGlysemicIndex() {
        return glysemicIndex;
    }
    public void setGlysemicIndex(String newGlysemicIndex) {
        this.glysemicIndex = newGlysemicIndex;
    }

}
