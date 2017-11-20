package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.SportForCardio;

/**
 * Created by Furkan Ekici on 21.11.2017.
 */

public interface CardioListener {
    public void CardioRead(List<SportForCardio> CardioList);
}
