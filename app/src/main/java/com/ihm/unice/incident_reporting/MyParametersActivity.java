package com.ihm.unice.incident_reporting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.ArraySet;
import android.widget.EditText;

import com.ihm.unice.incident_reporting.models.UrgentType;
import com.ihm.unice.incident_reporting.repositories.IncidentsRepository;
import com.ihm.unice.incident_reporting.repositories.RepositoryFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MyParametersActivity extends PreferenceActivity {

    private IncidentsRepository repository = RepositoryFactory.createRepository();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        for (UrgentType urgentType : repository.getUrgentTypes()) {
            Header header = new Header();
            header.title = "Paramètres de " + urgentType.getName();
            header.summary = "Change even more settings";
            header.fragment = PrefsUrgentCategory.class.getName();

            Bundle b = new Bundle();
            b.putString("type", urgentType.getName());
            header.fragmentArguments = b;
            target.add(header);
        }

    }

    public static class PrefsUrgentCategory extends PreferenceFragment {
        private SwitchPreference switchPreference;
        private CheckBoxPreference mat;
        private CheckBoxPreference fra;
        private CheckBoxPreference lyd;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.urgent_preferences);

            switchPreference = (SwitchPreference) findPreference("customNumberSwitch");
            mat = (CheckBoxPreference) findPreference("checkBoxMatthieu");
            fra = (CheckBoxPreference) findPreference("checkBoxFrancois");
            lyd = (CheckBoxPreference) findPreference("checkBoxLydie");
            switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    if (switchPreference.isChecked()) return true;
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Qui appeler ?");

                    final EditText input = new EditText(getActivity());
                    input.setHint("Entrez le numéro à appeler");

                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                    builder.setView(input);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switchPreference.setChecked(false);
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                    return true;
                }
            });

            mat.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    updateUsers(getArguments().getString("type"));
                    return mat.isChecked();
                }
            });

            fra.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    updateUsers(getArguments().getString("type"));
                    return fra.isChecked();
                }
            });

            lyd.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    updateUsers(getArguments().getString("type"));
                    return lyd.isChecked();
                }
            });


            setUIWithSharedPreferences(getArguments().getString("type"));
        }

        private void updateUsers(String key) {
            Set<String> users = new HashSet<>();
            if (fra.isChecked()) users.add("Francois");
            if (mat.isChecked()) users.add("Matthieu");
            if (lyd.isChecked()) users.add("Lydie");
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            sharedPref.edit().putStringSet("users" + key, users).apply();
        }

        private void setUIWithSharedPreferences(String type) {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

            boolean isChecked = sharedPref.getBoolean("custom" + type, false);
            switchPreference.setChecked(isChecked);

            Set<String> users = sharedPref.getStringSet("users" + type, new HashSet<String>());
            for (String user : users) {
                ((CheckBoxPreference) findPreference("checkBox" + user)).setChecked(true);
            }
        }

        private void writeSharedUsers(String key, List<String> values) {

        }


    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return PrefsUrgentCategory.class.getName().equals(fragmentName);
    }
}
