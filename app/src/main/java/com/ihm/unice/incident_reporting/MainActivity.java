package com.ihm.unice.incident_reporting;

import android.os.Bundle;

import com.ihm.unice.incident_reporting.models.MenuBaseActivity;

public class MainActivity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("App signalisation");
        setContentView(R.layout.activity_main);
    }

}
