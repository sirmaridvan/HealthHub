package halmob.healthhub.Models;

import java.util.Calendar;

/**
 * Created by hakan on 22.11.2017.
 */

public class CurrentTime {
    public String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        int Hr24 = c.get(Calendar.HOUR_OF_DAY);
        int Min = c.get(Calendar.MINUTE);
        String currentTime = String.valueOf(Hr24 + " " + Min);
        return currentTime;
    }
}
