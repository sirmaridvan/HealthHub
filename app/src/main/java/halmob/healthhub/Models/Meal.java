package halmob.healthhub.Models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hakan on 21.11.2017.
 */

public class Meal {
    private String foodName;
    private String foodType;
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

    public Float getTotalFat() {
        Float floatTotalFat = 0f;

        try {
            floatTotalFat = Float.parseFloat(totalFat.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatTotalFat;
    }

    public void setTotalFat(float newTotalFat) {
        this.totalFat = String.valueOf(newTotalFat * floatPortionSize);
    }

    public Float getTotalCarbohydrate() {
        Float floatTotalCarbohydrate = 0f;

        try {
            floatTotalCarbohydrate = Float.parseFloat(totalCarbohydrate.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatTotalCarbohydrate;
    }

    public void setTotalCarbohydrate(float newTotalCarbohydrate) {
        this.totalCarbohydrate = String.valueOf(newTotalCarbohydrate * floatPortionSize);
    }

    public Float getTotalCalorie() {
        Float floatTotalCalorie = 0f;

        try {
            floatTotalCalorie = Float.parseFloat(totalCalorie.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatTotalCalorie;
    }

    public void setTotalCalorie(float newTotalCalorie) {
        this.totalCalorie = String.valueOf(newTotalCalorie * floatPortionSize);
    }


    public Float getTotalFiber() {
        Float floatTotalFiber = 0f;

        try {
            floatTotalFiber = Float.parseFloat(totalFiber.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatTotalFiber;
    }

    public void setTotalFiber(float newTotalFiber) {
        this.totalFiber = String.valueOf(newTotalFiber * floatPortionSize);
    }

    public Float getTotalCarbohydrateCount() {
        Float floatTotalCarbohydrateCount = 0f;

        try {
            floatTotalCarbohydrateCount = Float.parseFloat(totalCarbohydrateCount.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatTotalCarbohydrateCount;
    }

    public void setTotalCarbohydrateCount(float newTotalCarbohydrateCount) {
        this.totalCarbohydrateCount = String.valueOf(newTotalCarbohydrateCount * floatPortionSize);
    }



    public Float getTotalGlycemicIndex() {
        Float floatTotalGlycemicIndex = 0f;

        try {
            floatTotalGlycemicIndex = Float.parseFloat(totalGlysemicIndex.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatTotalGlycemicIndex;
    }

    public void setTotalGlysemicIndex(float newTotalGlycemicIndex) {
        this.totalGlysemicIndex = String.valueOf(newTotalGlycemicIndex * floatPortionSize);
    }

    public String getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(String newPortionSize) {
        portionSize = newPortionSize;
    }


    public float getFloatPortionSize() {
        return floatPortionSize;
    }

    public void setFloatPortionSize(float newFloatPortionSize ) {
        floatPortionSize = newFloatPortionSize;
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
