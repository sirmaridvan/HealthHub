package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.SportProgramForCardio;

/**
 * Created by Furkan Ekici on 7.12.2017.
 */

public interface ProgramForCardioListener {
    public void CardioProgramRead(List<SportProgramForCardio> CardioProgramList);
}
