package halmob.healthhub.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RIDVAN SIRMA on 11/10/2017.
 */

@IgnoreExtraProperties
public class Drug {
    private String nameMedicine;
    private String howMany;
    private String startDate;
    private String endDate;
    private String time;
    private List <String> timeList = new ArrayList<>();

    public String getName() { return nameMedicine;}
    public void setName(String nameMedicine) { this.nameMedicine = nameMedicine; }

    public String getHowMany() {
        return howMany;
    }
    public void setHowMany(String howMany) {
        this.howMany = howMany;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public List<String> getTimeList() { return timeList; };
    public void setTimeList(List<String> timeList){ this.timeList = timeList; };


}
