package halmob.healthhub.Models;

/**
 * Created by hakan on 21.11.2017.
 */

public class CarbohydrateCountFood {
    String foodName;
    int gram;
    String portionSize;
    float carbohydrate;
    float fiber;
    float carbohydrateCount;
    int glysemicIndex;

    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String newFoodName) {
        this.foodName = newFoodName;
    }

    public int getGram() {
        return gram;
    }
    public void setGram(int newGram) { this.gram = newGram; }

    public String getPortionSize() {
        return portionSize;
    }
    public void setPortionSize(String newPortionSize) {
        this.portionSize = newPortionSize;
    }

    public float getCarbohydrate() {
        return carbohydrate;
    }
    public void setCarbohydrate(float newCarbohydrate) {
        this.carbohydrate = newCarbohydrate;
    }

    public float getFiber() { return fiber; }
    public void setFiber(float newFiber) {
        this.fiber = newFiber;
    }

    public float getCarbohydrateCount() {
        return carbohydrateCount;
    }
    public void setCarbohydrateCount(float newCarbohydrateCount) { this.carbohydrateCount = newCarbohydrateCount; }

    public int getGlysemicIndex() {
        return glysemicIndex;
    }
    public void setGlysemicIndex(int newGlysemicIndex) {
        this.glysemicIndex = newGlysemicIndex;
    }
}
