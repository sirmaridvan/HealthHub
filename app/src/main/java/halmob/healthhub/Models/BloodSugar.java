package halmob.healthhub.Models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hakan on 11.11.2017.
 */

public class BloodSugar {
    private int sugarValue;
    private String hungerSituation;
    private Date date;
    private String time;
    private String extraNotes;


    public int getBloodSugarValue() { return sugarValue; }
    public void setBloodSugarValue(int sugarValue) {
        this.sugarValue = sugarValue;
    }

    public String getHungerSituation() { return hungerSituation; }
    public void setHungerSituation(String hungerSituation) { this.hungerSituation = hungerSituation;}

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

    public String getExtraNotes() { return extraNotes; }
    public void setExtraNotes(String extraNotes) { this.extraNotes = extraNotes; }






}
