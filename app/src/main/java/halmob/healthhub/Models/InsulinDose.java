package halmob.healthhub.Models;

/**
 * Created by hakan on 11.11.2017.
 */

import java.util.Calendar;
import java.util.Date;


public class InsulinDose {
    Float appliedDose;
    Date date;
    String time;
    Integer insulinType;

    public Float getAppliedDose() { return appliedDose; }

    public void setAppliedDose(Float appliedDose) {
        this.appliedDose = appliedDose;
    }

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

    public Integer getInsulinType() { return insulinType; }

    public void setInsulinType(Integer insulinType) {
        this.insulinType = insulinType;
    }
}
