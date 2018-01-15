package com.ihm.unice.incident_reporting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.ihm.unice.incident_reporting.components.CustomAdapter;
import com.ihm.unice.incident_reporting.components.LastReportsAdapter;
import com.ihm.unice.incident_reporting.models.Incident;

import java.util.List;

import com.ihm.unice.incident_reporting.repositories.IncidentsRepository;

public class MainActivity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("App signalisation");
        setContentView(R.layout.activity_main);
        showBackButton(false);

        ListView listView = findViewById(R.id.listView);

        List<Incident> dataModels = IncidentsRepository.getAllIncidents();

        CustomAdapter<Incident> adapter = new LastReportsAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
    }

    public void onClickParametersUrgentIncident(View view){
        Intent intent = new Intent(this, ParametersActivity.class);
        startActivity(intent);
    }

    public void onClickReportUrgentIncident(View view){
        Intent intent = new Intent(this, UrgentReportActivity.class);
        startActivity(intent);
    }

    public void onClickReportNormalIncident(View view){
        Intent intent = new Intent(this, NormalReportActivity.class);
        startActivity(intent);
    }
}
