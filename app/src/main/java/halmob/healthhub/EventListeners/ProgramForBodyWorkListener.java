package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.SportProgramForBodyWork;

/**
 * Created by Furkan Ekici on 8.12.2017.
 */

public interface ProgramForBodyWorkListener {
    public void BodyWorkProgramRead(List<SportProgramForBodyWork> BodyWorkList);
}
