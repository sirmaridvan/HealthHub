package halmob.healthhub.Models;

/**
 * Created by hakan on 11.11.2017.
 */

import java.util.Calendar;
import java.util.Date;


public class InsulinDose {
    String appliedDose;
    Date date;
    String time;
    String insulinType;

    public String getAppliedDose() { return appliedDose; }

    public void setAppliedDose(String appliedDose) {
        this.appliedDose = appliedDose;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        Date currentDate = new Date(System.currentTimeMillis());
        this.date = currentDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        Calendar c = Calendar.getInstance();
        int Hr24=c.get(Calendar.HOUR_OF_DAY);
        int Min=c.get(Calendar.MINUTE);
        this.time = String.valueOf(Hr24 +  " " + Min);
    }

    public String getInsulinType() { return insulinType; }

    public void setInsulinType(String insulinType) {
        this.insulinType = insulinType;
    }
}
