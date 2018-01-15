package com.ihm.unice.incident_reporting;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ParametersActivity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameter_activity);

        Spinner spinner = findViewById(R.id.urgentSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.urgent_event, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


}
