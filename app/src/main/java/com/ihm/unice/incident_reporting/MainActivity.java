package com.ihm.unice.incident_reporting;

import android.os.Bundle;
import android.widget.ListView;

import com.ihm.unice.incident_reporting.components.CustomAdapter;
import com.ihm.unice.incident_reporting.components.LastReportsAdapter;
import com.ihm.unice.incident_reporting.models.Incident;
import com.ihm.unice.incident_reporting.models.User;

import java.util.ArrayList;
import java.util.List;

import com.ihm.unice.incident_reporting.models.MenuBaseActivity;

public class MainActivity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("App signalisation");
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        List<Incident> dataModels = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataModels.add(new Incident(new User("Roberto" + i)));

        }

        CustomAdapter<Incident> adapter = new LastReportsAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
    }

}
