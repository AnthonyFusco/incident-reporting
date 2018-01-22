package com.ihm.unice.incident_reporting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ihm.unice.incident_reporting.components.CustomAdapter;
import com.ihm.unice.incident_reporting.components.ParametersListUsersAdapter;
import com.ihm.unice.incident_reporting.models.viewmodels.ParametersUser;
import com.ihm.unice.incident_reporting.repositories.IncidentsRepository;

import java.util.List;

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

        ListView listView = findViewById(R.id.listView3);
        List<ParametersUser> dataModels = IncidentsRepository.getAllUsersParameters();
        CustomAdapter<ParametersUser> adapterListUsers = new ParametersListUsersAdapter(dataModels, getApplicationContext());
        listView.setAdapter(adapterListUsers);
    }

    public void onClickConfirm(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickAddEmergency(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ajouter une urgence");

        final EditText input = new EditText(this);
        input.setHint("Entrez le nom de l'urgence");

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            //save the new urgent type
        });
        builder.setNegativeButton("Annuler", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void onClickEditWhoCall(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Qui appeler ?");

        final EditText input = new EditText(this);
        input.setHint("Entrez le numéro à appeler");

        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            TextView textViewSaved = findViewById(R.id.textViewSavedNumber);
            textViewSaved.setText(input.getText());
        });
        builder.setNegativeButton("Annuler", (dialog, which) -> dialog.cancel());
        builder.show();
    }


}
