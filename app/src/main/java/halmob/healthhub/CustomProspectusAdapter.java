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

import halmob.healthhub.Models.ProspectusInfo;

/**
 * Created by gorkem on 6.12.2017.
 */

public class CustomProspectusAdapter extends ArrayAdapter<ProspectusInfo>{

    CustomProspectusAdapter(Context context, List<ProspectusInfo> prospectusList){
        super(context, R.layout.custom_prospectus_row, prospectusList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_prospectus_row, parent, false);

        final ProspectusInfo singleProspectusElement = getItem(position);

        TextView textView = (TextView) customView.findViewById(R.id.medicineNameInfo);
        ImageView imageView = (ImageView) customView.findViewById(R.id.prospectusPhoto);

        textView.setText("Medicine Name: " + singleProspectusElement.getMedName());

        FirebaseStorageUtility.loadImage(singleProspectusElement.getProspectusUri(),imageView, this.getContext());

        return customView;
    }
}
