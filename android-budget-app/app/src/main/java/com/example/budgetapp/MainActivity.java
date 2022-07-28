package com.example.budgetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MainFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        SettingsFragment settingsFragment = (SettingsFragment) getFragmentManager()
                .findFragmentById(R.id.settings_fragment);
        if (settingsFragment == null)
        {
            getMenuInflater().inflate(R.menu.fragment_main_activity, menu);
        }
        else
        {
            getMenuInflater().inflate(R.menu.fragment_main_activity_twopane, menu) ;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
            case R.id.menu_help:
                startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}