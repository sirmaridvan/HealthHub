package halmob.healthhub.Models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hakan on 11.11.2017.
 */

public class BloodSugar {
    private int sugarValue;
    private String hungerSituation;
    private String date;
    private String time;
    private String extraNotes;


    public int getBloodSugarValue() { return sugarValue; }
    public void setBloodSugarValue(int sugarValue) {
        this.sugarValue = sugarValue;
    }

    public String getHungerSituation() { return hungerSituation; }
    public void setHungerSituation(String hungerSituation) { this.hungerSituation = hungerSituation;}

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

    public String getExtraNotes() { return extraNotes; }
    public void setExtraNotes(String extraNotes) { this.extraNotes = extraNotes; }






}
