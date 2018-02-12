package com.ihm.unice.incident_reporting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import java.util.Date;
import java.util.List;

public class NormalReportPart3Activity extends MenuBaseActivity {
    private IncidentsRepository repository = RepositoryFactory.createRepository();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_report_who);

        listView = findViewById(R.id.litViewNormalListUsers);
        List<ParametersUser> dataModels = repository.getAllUsersSelectNormal();
        CustomAdapter<ParametersUser> adapterListUsers = new ParametersListUsersAdapter(dataModels, getApplicationContext());
        listView.setAdapter(adapterListUsers);


        setTitle("Informations supplémentaires");
    }

    public void onClickNext(View view){
        if (minOneSelected()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("toast", true);
            MainActivity.addIncident(new Incident(new User("Danièle"), new Date(),
                    ""));
            startActivity(intent);
            finish();
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            alertDialogBuilder.setTitle("Attention");
            alertDialogBuilder
                    .setMessage("Vous devez sélectionner au moins une personne à avertir")
                    .setCancelable(false)
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

            alertDialogBuilder.create().show();
        }

    }

    private boolean minOneSelected() {
        CustomAdapter<ParametersUser> adapterListUsers = (CustomAdapter<ParametersUser>) listView.getAdapter();
        for (ParametersUser parametersUser : adapterListUsers.getDataList()) {
            if (parametersUser.isSelected()) {
                return true;
            }
        }
        return false;
    }
}

