package halmob.healthhub.Models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hakan on 21.11.2017.
 */

public class Meal {
    private Food foodRecord; //Rıdvan tarafından incelenecek! Hazır yemeklerin çekilmesini nasıl yapacagımızla ilgili!
    private String portionSize;
    private Float floatPortionSize;
    private String date;
    private String time;
    private String totalGram;
    private String totalProtein;
    private String totalCarbohydrate;
    private String totalFat;
    private String totalCalorie;
    private String totalFiber;
    private String totalCarbohydrateCount;
    private String totalGlysemicIndex;


    //constructor of the Meal class
    public Meal(Food newFoodRecord, String newPortionSize) {
        this.foodRecord = newFoodRecord;
        this.portionSize = newPortionSize;

        try {
            this.floatPortionSize = Float.parseFloat(portionSize);
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing float error: " + NFE);
        }
        setTotalProtein(foodRecord.getProtein());
    }


    public Food getFoodRecord() { return foodRecord; }
    public void setFoodRecord(Food newFoodRecord) {
        this.foodRecord = newFoodRecord;
    }

    public String getPortion() { return portionSize; }
    public void setPortion(String newPortionSize ) {
        portionSize = newPortionSize;
    }

    public String getDate() {
        return date;
    }
    public void setDate() {
        CurrentDate newDate = new CurrentDate();
        this.date = newDate.getCurrentDate();
    }

    public String getTime() {
        return time;
    }
    public void setTime() {
        CurrentTime newTime = new CurrentTime();
        this.time = newTime.getCurrentTime();
    }


    public Float getTotalProtein() {
        Float floatTotalProtein = 0f;

        try {
            floatTotalProtein = Float.parseFloat(totalProtein.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatTotalProtein;
    }

    public void setTotalProtein(float newTotalProtein) {
        this.totalProtein = String.valueOf(newTotalProtein * floatPortionSize);
    }

    /*
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
    */
}
