package halmob.healthhub.Models;

/**
 * Created by gorkem on 25.11.2017.
 */

public class NotificationInfo {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String drugName;

    public void setYear(int year){ this.year = year; };
    public int getYear(){ return year; };

    public void setMonth(int month){ this.month = month; };
    public int getMonth(){ return month; };

    public void setDay(int day){ this.day = day; };
    public int getDay(){ return day; };

    public void setHour(int hour){ this.hour = hour; };
    public int getHour(){ return hour; };

    public void setMinute(int minute){ this.minute = minute; };
    public int getMinute(){ return minute; };

    public void setDrugName(String drugName){ this.drugName = drugName; };
    public String getDrugName(){ return drugName; };


}
