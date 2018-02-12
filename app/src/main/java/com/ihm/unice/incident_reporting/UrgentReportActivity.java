package com.ihm.unice.incident_reporting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ihm.unice.incident_reporting.models.Incident;
import com.ihm.unice.incident_reporting.models.UrgentType;
import com.ihm.unice.incident_reporting.models.User;
import com.ihm.unice.incident_reporting.repositories.IncidentsRepository;
import com.ihm.unice.incident_reporting.repositories.RepositoryFactory;

import java.util.Date;

public class UrgentReportActivity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.urgent_report);
    }

    public void baseOnClick() {

        Intent call = new Intent(Intent.ACTION_CALL);

        call.setData(Uri.parse("tel:" + "0613548760"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);

            return;
        }
        startActivityForResult(call, 42);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 42) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("toast", true);
            startActivity(intent);
            finish();
        }
    }

    public void onClickFire(View view) {
        MainActivity.addIncident(new Incident(new User("Danièle"), new Date(), "Incendie"));
        baseOnClick();
    }

    public void onClickInondation(View view) {
        MainActivity.addIncident(new Incident(new User("Danièle"), new Date(),"Inondation"));
        baseOnClick();
    }

    public void onClickGaz(View view) {
        MainActivity.addIncident(new Incident(new User("Danièle"), new Date(),"Gaz"));
        baseOnClick();
    }

    public void onClickMalaise(View view) {
        MainActivity.addIncident(new Incident(new User("Danièle"), new Date(),"Malaise"));
        baseOnClick();
    }

    public void onClickVol(View view) {
        MainActivity.addIncident(new Incident(new User("Danièle"), new Date(),"Vol"));
        baseOnClick();
    }

}
