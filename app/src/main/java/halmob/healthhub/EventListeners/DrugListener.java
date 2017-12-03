package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.Drug;

/**
 * Created by RIDVAN SIRMA on 11/15/2017.
 */
public interface DrugListener {
    public void drugsRead(List<Drug> drugList);

}