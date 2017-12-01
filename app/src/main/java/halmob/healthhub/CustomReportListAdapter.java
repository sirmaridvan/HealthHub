package halmob.healthhub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import halmob.healthhub.Models.Report;

/**
 * Created by gorkem on 1.12.2017.
 */

public class CustomReportListAdapter extends ArrayAdapter<Report>{

    CustomReportListAdapter(Context context, List<Report> reportList){
        super(context, R.layout.custom_analysis_report, reportList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_analysis_report, parent, false);

        final Report singleReportElement = getItem(position);

        TextView textView = (TextView) customView.findViewById(R.id.analysisInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.analysisPhoto);


        textView.setText("Report Name: " + singleReportElement.getReportName() + "\n" +
                         "Report Date: " + singleReportElement.getDate());

        FirebaseStorageUtility.loadImage(singleReportElement.getReportUri(),imageView, this.getContext());

        return customView;
    }

}
