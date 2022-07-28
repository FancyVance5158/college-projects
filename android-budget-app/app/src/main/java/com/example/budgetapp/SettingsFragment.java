package com.example.budgetapp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import androidx.preference.PreferenceManager;


public class SettingsFragment extends PreferenceFragment
implements OnSharedPreferenceChangeListener {

    //Class variables
    private SharedPreferences prefs;

    private String percentages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.root_preferences);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    private boolean validateCustomPercentages(String percentages) {
        //Validates the custom percentages for the "Custom Budget Percentage" preference
        //They must be formatted correctly and must add up to 100
        String[] values = percentages.split(", ");
        int sum = 0;
        for (String i : values) {
            int value = Integer.parseInt(i);
            sum += value;
        }
        if (sum == 100) { return true; }
        else { return false; }
    }

    @Override
    public void onPause() {
        super.onPause();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        //Event handler for changing a preference
        MainFragment mainFragment = (MainFragment) getFragmentManager()
                .findFragmentById(R.id.main_fragment);

        if (mainFragment != null) {
            mainFragment.onResume();
        }

        //Validates the percentages and alerts the user whether it was successful
        if (key.equals("pref_custom_percentages")) {
            percentages = prefs.getString(key, "60, 20, 10, 10");
            if (!validateCustomPercentages(percentages)) {
                prefs.edit().remove("pref_custom_percentages");
                Toast.makeText(getActivity(), "Operation Not Successful: Percentages don't add up to 100.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
