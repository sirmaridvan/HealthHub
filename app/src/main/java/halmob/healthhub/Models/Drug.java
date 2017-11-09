package halmob.healthhub.Models;

/**
 * Created by RIDVAN SIRMA on 11/10/2017.
 */

public class Drug {
    String name;
    String howMany;
    String endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHowMany() {
        return howMany;
    }

    public void setHowMany(String howMany) {
        this.howMany = howMany;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
