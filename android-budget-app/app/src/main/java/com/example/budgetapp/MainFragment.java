package com.example.budgetapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import androidx.preference.PreferenceManager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainFragment extends Fragment
implements OnClickListener {

    //Widget variables
    private TextView MortgageTextView;
    private TextView GroceriesTextView;
    private TextView GasTextView;
    private TextView SpendingTextView;
    private Button PaymentButton;
    private Button HistoryButton;

    //Variables to hold the category values
    private double MortgageValue;
    private double GroceriesValue;
    private double GasValue;
    private double SpendingValue;

    //Preference variables
    private SharedPreferences prefs;
    private boolean allowNegativeBalance = true;
    private int deleteHistoryAfter = 5;
    private String customPercentageValues = "60, 20, 10, 10";

    //Database variables
    public static BudgetEntryDB beDB;
    public static CategoryDB cDB;
    StringBuilder sb;

    ArrayList<Double> categories;
    ArrayList<BudgetEntry> entries;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(getActivity(), R.xml.root_preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        //Creates a new instance of each database
        beDB = new BudgetEntryDB(getActivity());
        cDB = new CategoryDB(getActivity());
        sb = new StringBuilder();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //Set widgets
        MortgageTextView = (TextView) view.findViewById(R.id.txtMortgagePayment);
        GroceriesTextView = (TextView) view.findViewById(R.id.txtGroceries);
        GasTextView = (TextView) view.findViewById(R.id.txtGas);
        SpendingTextView = (TextView) view.findViewById(R.id.txtSpending);

        PaymentButton = (Button) view.findViewById(R.id.btnEnterPayment);
        HistoryButton = (Button) view.findViewById(R.id.btnPaymentHistory);

        //Set listeners
        PaymentButton.setOnClickListener(this);
        HistoryButton.setOnClickListener(this);

        //Update the information from the database
        updateFromDB();
        return view;
    }

    @Override
    public void onPause() { super.onPause(); }

    @Override
    public void onResume()
    {
        super.onResume();

        //Update from the database again
        updateFromDB();

        //Delete entries older than time set in preferences
        deleteFromDB();

        //Load preference variables
        allowNegativeBalance = prefs.getBoolean("pref_negative_balance", true);
        deleteHistoryAfter = Integer.parseInt(prefs.getString("pref_delete_history", "5"));
        customPercentageValues = prefs.getString("pref_custom_percentage", "60, 20, 10, 10");
    }

    public void updateFromDB()
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        //Get category values and entries
        categories= cDB.getCategoryValues();
        entries = beDB.getBudgetEntries();

        MortgageValue = categories.get(0);
        GroceriesValue = categories.get(1);
        GasValue = categories.get(2);
        SpendingValue = categories.get(3);

        //Set each TextView to its value
        MortgageTextView.setText(formatter.format(MortgageValue));
        GroceriesTextView.setText(formatter.format(GroceriesValue));
        GasTextView.setText(formatter.format(GasValue));
        SpendingTextView.setText(formatter.format(SpendingValue));
    }

    public void deleteFromDB() {
        //Deletes entries from the database based on their age and
        //the time frame selected in the "Delete History After..." preference

        //Creates a calendar object and sets the date to a certain time in the past
        //5 is "Never"
        Calendar cal = Calendar.getInstance();
        switch (deleteHistoryAfter) {
            case 0:
                cal.add(Calendar.DAY_OF_MONTH, -7);
                break;
            case 1:
                cal.add(Calendar.DAY_OF_MONTH, -14);
                break;
            case 2:
                cal.add(Calendar.MONTH, -1);
                break;
            case 3:
                cal.add(Calendar.MONTH, -3);
                break;
            case 4:
                cal.add(Calendar.YEAR, -1);
                break;
            case 5:
                cal.add(Calendar.YEAR, -100);
                break;
        }

        //Deletes entries older than the Calendar object
        for (BudgetEntry entry : entries) {
            long entryDate = Long.parseLong(entry.getDate());
            long cutoffDate = cal.getTimeInMillis();
            if (entryDate < cutoffDate) {
                beDB.deleteEntry(entry.getEntryId());
            }
        }
    }

    //Getter method for both database instances
    public static BudgetEntryDB getBeDB () { return beDB; }
    public static CategoryDB getcDB () { return cDB; }

    @Override
    public void onClick(View view) {
        //Opens other two activities
        switch (view.getId())
        {
            case R.id.btnEnterPayment:
                startActivity(new Intent(getActivity(), SecondaryActivity.class));
                break;
            case R.id.btnPaymentHistory:
                startActivity(new Intent(getActivity(), AdditionalActivity.class));
                break;
        }
    }
}