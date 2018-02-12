package com.ihm.unice.incident_reporting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NormalReportPart2Activity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_report_where);
        setTitle("Pièce concernée");

    }

    public void onClickNext(View view){
        Intent intent = new Intent(this, NormalReportPart3Activity.class);
        startActivity(intent);
        finish();
    }
}

