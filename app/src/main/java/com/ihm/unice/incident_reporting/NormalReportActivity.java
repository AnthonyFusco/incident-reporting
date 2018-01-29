package com.ihm.unice.incident_reporting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.ihm.unice.incident_reporting.components.CustomAdapter;
import com.ihm.unice.incident_reporting.components.ParametersListUsersAdapter;
import com.ihm.unice.incident_reporting.models.Incident;
import com.ihm.unice.incident_reporting.models.User;
import com.ihm.unice.incident_reporting.models.viewmodels.ParametersUser;
import com.ihm.unice.incident_reporting.repositories.IncidentsRepository;
import com.ihm.unice.incident_reporting.repositories.RepositoryFactory;
import com.ihm.unice.incident_reporting.repositories.RepositoryIncidentsBase;

import java.util.Date;
import java.util.List;

public class NormalReportActivity extends MenuBaseActivity {

    private IncidentsRepository repository = RepositoryFactory.createRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_report);

        Spinner spinner = findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_event_normal, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ListView listView = findViewById(R.id.litViewNormalListUsers);
        List<ParametersUser> dataModels = repository.getAllUsersSelectNormal();
        CustomAdapter<ParametersUser> adapterListUsers = new ParametersListUsersAdapter(dataModels, getApplicationContext());
        listView.setAdapter(adapterListUsers);
    }

    public void onClickSignalize(View view){
        Intent intent = new Intent(this, ConfirmIncidentActivity.class);
        MainActivity.addIncident(new Incident(new User("Danièle"), new Date(), "temp"));
        startActivity(intent);
        finish();
    }
}
