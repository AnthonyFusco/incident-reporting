package com.ihm.unice.incident_reporting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NormalReportPart1Activity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_report_v2);
        setTitle("Type d'incident");
    }

    public void onClickNext(View view){
        Intent intent = new Intent(this, NormalReportPart3Activity.class);
        startActivity(intent);
        finish();
    }
}
