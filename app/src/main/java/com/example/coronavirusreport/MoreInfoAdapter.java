package com.example.coronavirusreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MoreInfoAdapter extends ArrayAdapter<MoreInfo> {
    public MoreInfoAdapter(@NonNull Context context, int resource, @NonNull List<MoreInfo> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.more_info_item, parent, false);
        }
        MoreInfo current = getItem(position);

        TextView title = listItemView.findViewById(R.id.title_textView);
        title.setText(current.getTitle());

        return listItemView;
    }
}
