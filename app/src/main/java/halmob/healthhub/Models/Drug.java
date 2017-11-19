package halmob.healthhub.Models;

/**
 * Created by RIDVAN SIRMA on 11/10/2017.
 */

public class Drug {
    String nameMedicine;
    String howMany;
    String startDate;
    String endDate;
    String time;

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

}
