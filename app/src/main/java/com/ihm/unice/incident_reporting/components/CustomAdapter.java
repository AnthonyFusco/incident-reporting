package com.ihm.unice.incident_reporting.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CustomAdapter<T> extends ArrayAdapter<T> {

    private List<T> dataList;
    private int layout;


    public CustomAdapter(List<T> dataList, int layout, Context context) {
        super(context, layout, dataList);
        this.layout = layout;
        this.dataList = dataList;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        T dataModel = getItem(position);

        Map<String, View> viewHolder = new HashMap<>();

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);

            configureViewHolder(viewHolder, convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (Map<String, View>) convertView.getTag();
        populateViewHolder(viewHolder, dataModel);

        return convertView;
    }

    public abstract void populateViewHolder(Map<String, View> viewHolder, T dataModel);
    public abstract void configureViewHolder(Map<String, View> viewHolder, View convertView);
}