package halmob.healthhub.Models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hakan on 21.11.2017.
 */

public class Meal {
    private Food foodRecord; //Rıdvan tarafından incelenecek! Hazır yemeklerin çekilmesini nasıl yapacagımızla ilgili!
    private String portionSize;
    private String date;
    private String time;

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
}
