package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.InsulinDose;

/**
 * Created by hakan on 22.11.2017.
 */

public interface InsulinDoseListener {
    public void insulinDosesRead(List<InsulinDose> insulinDoseList);
}
