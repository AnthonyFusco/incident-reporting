package com.ihm.unice.incident_reporting.components;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ihm.unice.incident_reporting.R;
import com.ihm.unice.incident_reporting.models.Incident;

import java.util.List;
import java.util.Map;

public class LastReportsAdapter extends CustomAdapter<Incident> {

    private static final String NAME = "name";

    public LastReportsAdapter(List<Incident> dataList, Context context) {
        super(dataList, R.layout.last_reports_row, context);
    }

    @Override
    public void populateViewHolder(Map<String, View> viewHolder, Incident dataModel) {
        ((TextView)viewHolder.get(NAME)).setText(dataModel.getUser().getName());
    }

    @Override
    public void configureViewHolder(Map<String, View> viewHolder, View convertView) {
        viewHolder.put(NAME, convertView.findViewById(R.id.textViewLastReportsRowName));
    }
}
