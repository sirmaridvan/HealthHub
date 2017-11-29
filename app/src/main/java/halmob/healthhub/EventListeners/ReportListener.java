package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.Person;
import halmob.healthhub.Models.Report;

/**
 * Created by RIDVAN SIRMA on 11/30/2017.
 */

public interface ReportListener {
    public void reportRead(List<Report> reportList);
}
