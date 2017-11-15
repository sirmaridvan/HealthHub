package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.Drug;
import halmob.healthhub.Models.Person;

/**
 * Created by RIDVAN SIRMA on 11/15/2017.
 */

public interface PeopleListener {
    public void peopleRead(List<Person> personList);
}
