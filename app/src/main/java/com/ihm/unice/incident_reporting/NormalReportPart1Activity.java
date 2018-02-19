package com.ihm.unice.incident_reporting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NormalReportPart1Activity extends MenuBaseActivity {

    public static String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_report_v2);
        setTitle("Type d'incident");
    }

    public void onClickNext(final View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Que se passe t-il ?");

        final EditText input = new EditText(this);
        input.setHint("Expliquez le problème");

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        final Context context = this;
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NormalReportPart1Activity.type = view.getTag().toString();
                dialogInterface.cancel();
                Intent intent = new Intent(context, NormalReportPart2Activity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });
        builder.show();


    }
}
