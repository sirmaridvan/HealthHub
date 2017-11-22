package halmob.healthhub.Models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hakan on 21.11.2017.
 */

public class Meal {
    private String foodName; //Rıdvan tarafından incelenecek! Hazır yemeklerin çekilmesini nasıl yapacagımızla ilgili!
    private String date;
    private String time;

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
