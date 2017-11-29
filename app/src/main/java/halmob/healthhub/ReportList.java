package halmob.healthhub;

/**
 * Created by RIDVAN SIRMA on 11/30/2017.
 */

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ReportList extends ArrayAdapter<String>{

    private final Activity activity;
    private final Context context;
    private final String[] uri;
    public ReportList(Activity activity,Context context,
                      String[] uri) {
        super(activity, R.layout.list_single, uri);
        this.activity = activity;
        this.context = context;
        this.uri = uri;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);

        ImageView imageView =  rowView.findViewById(R.id.img);
        FirebaseStorageUtility.loadImage(uri[position],imageView,context);
        return rowView;
    }
}