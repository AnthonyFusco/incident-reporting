package com.ihm.unice.incident_reporting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.ihm.unice.incident_reporting.components.CustomAdapter;
import com.ihm.unice.incident_reporting.components.LastReportsAdapter;
import com.ihm.unice.incident_reporting.models.Incident;

import java.util.List;

import com.ihm.unice.incident_reporting.models.User;
import com.ihm.unice.incident_reporting.repositories.IncidentsRepository;
import com.ihm.unice.incident_reporting.repositories.RepositoryFactory;
import com.ihm.unice.incident_reporting.repositories.RepositoryIncidentsBase;

public class MainActivity extends MenuBaseActivity {
    private static IncidentsRepository repository = RepositoryFactory.createRepository();
    private static CustomAdapter<Incident> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("App signalisation");
        setContentView(R.layout.activity_main);
        showBackButton(false);

        ListView listView = findViewById(R.id.listView);
        List<Incident> dataModels = repository.getAllIncidents();

        adapter = new LastReportsAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
    }

    public static void addIncident(Incident incident) {
        repository.addIncident(incident);
        adapter.notifyDataSetChanged();
    }

    public void onClickReportUrgentIncident(View view){
        Intent intent = new Intent(this, UrgentReportActivity.class);
        startActivity(intent);
    }

    public void onClickReportNormalIncident(View view){
        Intent intent = new Intent(this, NormalReportPart1Activity.class);
        startActivity(intent);
    }
}
