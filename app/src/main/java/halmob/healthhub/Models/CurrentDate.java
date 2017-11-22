package halmob.healthhub.Models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hakan on 22.11.2017.
 */

public class CurrentDate {
    public String getCurrentDate() {
        Calendar currentCalendar = Calendar.getInstance();
        SimpleDateFormat newSDF = new SimpleDateFormat("dd-MMMM-YYYY");
        String currentDate = newSDF.format(currentCalendar.getTime());
        return currentDate;
    }
}
