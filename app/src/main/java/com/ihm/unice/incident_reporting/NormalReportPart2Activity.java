package com.ihm.unice.incident_reporting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;

import com.ihm.unice.incident_reporting.ConfirmIncidentActivity;
import com.ihm.unice.incident_reporting.MainActivity;
import com.ihm.unice.incident_reporting.MenuBaseActivity;
import com.ihm.unice.incident_reporting.R;
import com.ihm.unice.incident_reporting.components.CustomAdapter;
import com.ihm.unice.incident_reporting.components.ParametersListUsersAdapter;
import com.ihm.unice.incident_reporting.models.Incident;
import com.ihm.unice.incident_reporting.models.User;
import com.ihm.unice.incident_reporting.models.viewmodels.ParametersUser;
import com.ihm.unice.incident_reporting.repositories.IncidentsRepository;
import com.ihm.unice.incident_reporting.repositories.RepositoryFactory;

import java.util.Date;
import java.util.List;

public class NormalReportPart2Activity extends MenuBaseActivity {

    private IncidentsRepository repository = RepositoryFactory.createRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_report_people);

        ListView listView = findViewById(R.id.litViewNormalListUsers);
        List<ParametersUser> dataModels = repository.getAllUsersSelectNormal();
        CustomAdapter<ParametersUser> adapterListUsers = new ParametersListUsersAdapter(dataModels, getApplicationContext());
        listView.setAdapter(adapterListUsers);

    }

    public void onClickNext(View view){
        Intent intent = new Intent(this, NormalReportPart3Activity.class);

        startActivity(intent);
        finish();
    }
}

