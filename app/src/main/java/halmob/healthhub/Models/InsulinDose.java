package halmob.healthhub.Models;

/**
 * Created by hakan on 11.11.2017.
 */

import java.util.Calendar;

public class InsulinDose {
    private Float appliedDose;
    private String date;
    private String time;
    private String insulinType;

    public Float getAppliedDose() { return appliedDose; }
    public void setAppliedDose(Float appliedDose) {
        this.appliedDose = appliedDose;
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

    public String getInsulinType() { return insulinType; }
    public void setInsulinType(String insulinType) {
        this.insulinType = insulinType;
    }
}
