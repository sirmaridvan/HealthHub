package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.SportForBodyWork;

/**
 * Created by Furkan Ekici on 20.11.2017.
 */

public interface BodyWorkListener {
    public void BodyWorkRead(List<SportForBodyWork> BodyWorkList);
}
