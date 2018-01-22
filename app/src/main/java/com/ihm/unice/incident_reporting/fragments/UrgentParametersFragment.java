package com.ihm.unice.incident_reporting.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ihm.unice.incident_reporting.MainActivity;
import com.ihm.unice.incident_reporting.R;
import com.ihm.unice.incident_reporting.components.CustomAdapter;
import com.ihm.unice.incident_reporting.components.ParametersListUsersAdapter;
import com.ihm.unice.incident_reporting.models.viewmodels.ParametersUser;
import com.ihm.unice.incident_reporting.repositories.IncidentsRepository;

import java.util.List;

public class UrgentParametersFragment extends AbstractFragment {

    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.urgent_parameters_fragment, container, false);

        Spinner spinner = rootView.findViewById(R.id.urgentSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.urgent_event, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ListView listView = rootView.findViewById(R.id.listView3);
        List<ParametersUser> dataModels = IncidentsRepository.getAllUsersParameters();
        CustomAdapter<ParametersUser> adapterListUsers = new ParametersListUsersAdapter(dataModels, getActivity());
        listView.setAdapter(adapterListUsers);

        rootView.findViewById(R.id.button2).setOnClickListener(this::onClickEditWhoCall);
        rootView.findViewById(R.id.button3).setOnClickListener(this::onClickConfirm);
        rootView.findViewById(R.id.addUrgency).setOnClickListener(this::onClickAddEmergency);
        return rootView;
    }

    public void onClickConfirm(View view){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void onClickAddEmergency(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ajouter une urgence");

        final EditText input = new EditText(getActivity());
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Qui appeler ?");

        final EditText input = new EditText(getActivity());
        input.setHint("Entrez le numéro à appeler");

        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            TextView textViewSaved = rootView.findViewById(R.id.textViewSavedNumber);
            textViewSaved.setText(input.getText());
        });
        builder.setNegativeButton("Annuler", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public String getTitle() {
        return "Paramètres incident urgent";
    }
}
