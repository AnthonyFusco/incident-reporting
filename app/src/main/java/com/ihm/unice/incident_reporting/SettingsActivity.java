package com.ihm.unice.incident_reporting;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

import com.ihm.unice.incident_reporting.models.UrgentType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SettingsActivity extends AppCompatPreferenceActivity {

    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }

    private void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }


    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    @Override
    public void onHeaderClick(Header header, int position) {
        if(header.fragmentArguments == null){
            header.fragmentArguments = new Bundle();
        }
        header.fragmentArguments.putInt("position", position);
        super.onHeaderClick(header, position);
    }


    @Override
    protected boolean isValidFragment(String fragmentName) {
        return SettingsActivity.PrefsUrgentCategory.class.getName().equals(fragmentName);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class PrefsUrgentCategory extends PreferenceFragment {
        private SwitchPreference switchPreference;
        //private CheckBoxPreference mat;
        //private CheckBoxPreference fra;
        //private CheckBoxPreference lyd;
        //private EditTextPreference editText;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.urgent_preferences);
            switchPreference = (SwitchPreference) findPreference("customNumberSwitch");
            //mat = (CheckBoxPreference) findPreference("checkBoxMatthieu");
            //fra = (CheckBoxPreference) findPreference("checkBoxFrancois");
            //lyd = (CheckBoxPreference) findPreference("checkBoxLydie");
            //editText = (EditTextPreference) findPreference("vv");
            final EditText input = new EditText(getActivity());
            switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    if (switchPreference.isChecked()) return true;
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Qui appeler ?");


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
                    input.requestFocus();

                    return true;
                }
            });

            ListPreference list = (ListPreference) findPreference("language_preference");
            int position = getArguments().getInt("position", 0);
            switch (position) {
                case 0:
                    list.setValueIndex(0);
                    break;
                case 1:
                    list.setValueIndex(1);
                    break;
                case 2:
                    list.setValueIndex(0);
                    break;
                case 3:
                    list.setValueIndex(2);
                    break;
                case 4:
                    list.setValueIndex(0);
                    break;
            }
            switchPreference.setChecked(false);
            //getArguments().getString("type");
            //setUIWithSharedPreferences(getArguments().getString("type"));

        }

    }
}
