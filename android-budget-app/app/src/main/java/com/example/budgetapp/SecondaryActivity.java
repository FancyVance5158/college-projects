package com.example.budgetapp;

import android.app.Activity;
import android.os.Bundle;

public class SecondaryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SecondaryFragment()).commit();
    }
}