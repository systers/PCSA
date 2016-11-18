package com.peacecorps.pcsa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * This activity handles storing and modifying users preferences
 * @author rohan
 * @since 2016-07-20.
 */
public class UserSettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preferences);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Display the fragment as the main content
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        private SharedPreferences sharedPreferences;
        private static String currentName;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.user_settings);
            // show the current value in the settings screen
            for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
                pickPreferenceObject(getPreferenceScreen().getPreference(i));
            }
        }

        private void pickPreferenceObject(Preference p) {
            if (p instanceof PreferenceCategory) {
                PreferenceCategory cat = (PreferenceCategory) p;
                for (int i = 0; i < cat.getPreferenceCount(); i++) {
                    pickPreferenceObject(cat.getPreference(i));
                }
            } else {
                initSummary(p);
            }
        }

        private void initSummary(Preference p) {

            if (p instanceof EditTextPreference) {
                EditTextPreference editTextPref = (EditTextPreference) p;
                p.setSummary(editTextPref.getText());
                currentName = editTextPref.getText();
            }
            if (p instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) p;
                p.setSummary(listPreference.getValue());
            }
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if("".equals(sharedPreferences.getString(key,"").trim())){
                Toast.makeText(getActivity(),R.string.valid_name, Toast.LENGTH_SHORT).show();
                return;
            }
            if(key.equals(getString(R.string.key_name)) || key.equals(getString(R.string.key_country)))
            {
                Preference preference = findPreference(key);
                preference.setSummary(sharedPreferences.getString(key.trim(),""));
                MainActivity.refreshList = true;
            }

        }

        @Override
        public void onResume() {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            sharedPreferences.registerOnSharedPreferenceChangeListener(this);
            super.onResume();
        }

        @Override
        public void onPause() {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            if(sharedPreferences.getString("NAME","").trim().equals("")){
                sharedPreferences.edit().putString("NAME",currentName).apply();
            }
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
}