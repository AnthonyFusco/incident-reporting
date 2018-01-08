package com.ihm.unice.incident_reporting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ihm.unice.incident_reporting.models.MenuBaseActivity;

public class NormalReportActivity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
