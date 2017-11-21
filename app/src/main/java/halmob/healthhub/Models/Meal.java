package halmob.healthhub.Models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hakan on 21.11.2017.
 */

public class Meal {
    String foodName; //Rıdvan tarafından incelenecek! Hazır yemeklerin çekilmesini nasıl yapacagımızla ilgili!
    Date date;
    String time;

    public Date getDate() {
        return date;
    }
    public void setDate() {
        Date currentDate = new Date(System.currentTimeMillis());
        this.date = currentDate;
    }

    public String getTime() {
        return time;
    }
    public void setTime() {
        Calendar c = Calendar.getInstance();
        int Hr24=c.get(Calendar.HOUR_OF_DAY);
        int Min=c.get(Calendar.MINUTE);
        this.time = String.valueOf(Hr24 +  " " + Min);
    }
}
