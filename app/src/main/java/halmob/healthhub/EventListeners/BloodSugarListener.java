package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.BloodSugar;

/**
 * Created by hakan on 18.11.2017.
 */

public interface BloodSugarListener {
    public void bloodSugarsRead(List<BloodSugar> bloodSugarList);
}
