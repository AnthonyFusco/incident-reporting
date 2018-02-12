package com.ihm.unice.incident_reporting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.ihm.unice.incident_reporting.components.CustomAdapter;
import com.ihm.unice.incident_reporting.components.ParametersListUsersAdapter;
import com.ihm.unice.incident_reporting.models.viewmodels.ParametersUser;
import com.ihm.unice.incident_reporting.repositories.IncidentsRepository;
import com.ihm.unice.incident_reporting.repositories.RepositoryFactory;

import java.util.List;

public class NormalReportPart3Activity extends MenuBaseActivity {
    private IncidentsRepository repository = RepositoryFactory.createRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_report_piece);

        ListView listView = findViewById(R.id.litViewNormalListUsers);
        List<ParametersUser> dataModels = repository.getAllUsersSelectNormal();
        CustomAdapter<ParametersUser> adapterListUsers = new ParametersListUsersAdapter(dataModels, getApplicationContext());
        listView.setAdapter(adapterListUsers);

        Spinner spinnerPieces = findViewById(R.id.editText3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.type_pieces, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPieces.setAdapter(adapter2);
        setTitle("Informations supplémentaires");
    }

    public void onClickNext(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("toast", true);
        startActivity(intent);
        finish();
    }
}

