package com.ihm.unice.incident_reporting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ihm.unice.incident_reporting.models.UrgentType;
import com.ihm.unice.incident_reporting.repositories.IncidentsRepository;

public class UrgentReportActivity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.urgent_report);

        Spinner spinner = findViewById(R.id.urgentSpinner);

        ArrayAdapter<UrgentType> adapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,
                        IncidentsRepository.getUrgentTypes());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner whoCall = findViewById(R.id.who_call);
        ArrayAdapter<CharSequence> whoCallAdapter = ArrayAdapter.createFromResource(this,
                R.array.who_call, android.R.layout.simple_spinner_item);
        whoCallAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whoCall.setAdapter(whoCallAdapter);

        Spinner whoSignal = findViewById(R.id.who);
        ArrayAdapter<CharSequence> whoSignalAdapter = ArrayAdapter.createFromResource(this,
                R.array.who, android.R.layout.simple_spinner_item);
        whoSignalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whoSignal.setAdapter(whoSignalAdapter);
    }

    public void onClickSignalize(View view){
        Intent intent = new Intent(this, ConfirmIncidentActivity.class);
        startActivity(intent);
        finish();
    }
}
