package halmob.healthhub;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import halmob.healthhub.Models.Person;

/**
 * Created by RIDVAN SIRMA on 12/3/2017.
 */

public class UserList extends ArrayAdapter<Person> {
    private final Activity context;
    private final List<Person> personLists;
    public UserList(Activity context,
                    List<Person> personLists) {
        super(context, R.layout.user_list,personLists);
        this.context = context;
        this.personLists=personLists;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.user_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.user_name);
        txtTitle.setText(personLists.get(position).getDisplayName());
        return rowView;
    }
}