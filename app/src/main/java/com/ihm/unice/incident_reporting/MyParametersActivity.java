package com.ihm.unice.incident_reporting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.ArraySet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
            header.title = urgentType.getName();
            header.summary = "Modifiez les paramètres pour " + urgentType.getName();
            header.fragment = PrefsUrgentCategory.class.getName();

            Bundle b = new Bundle();
            b.putString("type", urgentType.getName().substring(0,2));
            header.fragmentArguments = b;
            b.putSerializable("obj", urgentType);
            target.add(header);
        }

    }

    public static class PrefsUrgentCategory extends PreferenceFragment {
        private SwitchPreference switchPreference;
        private CheckBoxPreference mat;
        private CheckBoxPreference fra;
        private CheckBoxPreference lyd;
        //private EditTextPreference editText;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.urgent_preferences);
            switchPreference = (SwitchPreference) findPreference("customNumberSwitch");
            mat = (CheckBoxPreference) findPreference("checkBoxMatthieu");
            fra = (CheckBoxPreference) findPreference("checkBoxFrancois");
            lyd = (CheckBoxPreference) findPreference("checkBoxLydie");
            //editText = (EditTextPreference) findPreference("vv");
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

            UrgentType urgentType = (UrgentType) getArguments().getSerializable("obj");
            ListPreference list = (ListPreference) findPreference("language_preference");
            list.setValueIndex(urgentType != null ? urgentType.getBaseNumber() : 0);
            setUIWithSharedPreferences(getArguments().getString("type"));

        }

        private void writePreferences(String key) {
            Set<String> users = new HashSet<>();
            if (fra.isChecked()) users.add("Francois");
            if (mat.isChecked()) users.add("Matthieu");
            if (lyd.isChecked()) users.add("Lydie");
            System.out.println(users);
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            sharedPref.edit().putStringSet("users" + key, users).apply();

            sharedPref.edit().putBoolean("custom" + key, switchPreference.isChecked()).apply();
            //sharedPref.edit().putString("number" + key, editText.getText()).apply();
        }

        private void setUIWithSharedPreferences(String type) {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

            boolean isChecked = sharedPref.getBoolean("custom" + type, false);
            switchPreference.setChecked(isChecked);
            //editText.setText(sharedPref.getString("number" + type, ""));
            Set<String> users = sharedPref.getStringSet("users" + type, new HashSet<String>());
            for (String user : users) {
                ((CheckBoxPreference) findPreference("checkBox" + user)).setChecked(true);
            }
        }

        @Override
        public void onPause() {
            System.out.println("save ");
            writePreferences(getArguments().getString("type"));
            super.onPause();
        }
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return PrefsUrgentCategory.class.getName().equals(fragmentName);
    }
}
