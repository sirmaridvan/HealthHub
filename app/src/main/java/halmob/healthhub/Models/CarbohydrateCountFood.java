package halmob.healthhub.Models;

/**
 * Created by hakan on 21.11.2017.
 */

public class CarbohydrateCountFood {
    private String foodName;
    private String foodType;
    private String gram;
    private String portionSize;
    private String carbohydrate;
    private String fiber;
    private String carbohydrateCount;
    private String glysemicIndex;


    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String newFoodName) {
        this.foodName = newFoodName;
    }

    public String getFoodType() {
        return foodType;
    }
    public void setFoodType(String newFoodType) {
        this.foodType = newFoodType;
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

    public String getCarbohydrate() {
        return carbohydrate;
    }
    public void setCarbohydrate(String newCarbohydrate) {
        this.carbohydrate = newCarbohydrate;
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
