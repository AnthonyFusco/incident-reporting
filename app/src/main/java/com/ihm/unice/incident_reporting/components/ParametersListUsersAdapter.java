package com.ihm.unice.incident_reporting.components;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ihm.unice.incident_reporting.R;
import com.ihm.unice.incident_reporting.models.viewmodels.ParametersUser;

import java.util.List;
import java.util.Map;

public class ParametersListUsersAdapter extends CustomAdapter<ParametersUser> {

    private static final String NAME = "name";
    private static final String CHECKBOX = "selected";

    public ParametersListUsersAdapter(List<ParametersUser> dataList, Context context) {
        super(dataList, R.layout.parameters_list_users_row, context);
    }

    @Override
    public void populateViewHolder(Map<String, View> viewHolder, ParametersUser dataModel) {
        ((TextView)viewHolder.get(NAME)).setText(dataModel.getUser().getName());
        ((CheckBox)viewHolder.get(CHECKBOX)).setChecked(dataModel.isSelected());
    }

    @Override
    public void configureViewHolder(Map<String, View> viewHolder, View convertView) {
        viewHolder.put(NAME, convertView.findViewById(R.id.parametersListUserRowName));
        viewHolder.put(CHECKBOX, convertView.findViewById(R.id.parametersListUserRowCheckbox));
    }
}
