package com.example.budgetapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Date;

public class SecondaryFragment extends Fragment
implements OnClickListener, OnCheckedChangeListener, OnEditorActionListener {

    //Widget variables
    private RadioGroup paymentRadioGroup;
    private RadioButton paycheckRadioButton;
    private EditText valueEditText;
    private Spinner categorySpinner;
    private Button goButton;

    //Other variables
    private boolean PaymentChecked = true;
    private double value;
    private SharedPreferences prefs;

    //Databases are initialized using the static getter methods from Main Fragment
    private BudgetEntryDB beDB = MainFragment.getBeDB();
    private CategoryDB cDB = MainFragment.getcDB();

    private ArrayList<Double> categories = cDB.getCategoryValues();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_secondary, container, false);

        //Set widgets
        paymentRadioGroup = (RadioGroup) view.findViewById(R.id.rdoSelector);
        paycheckRadioButton = (RadioButton) view.findViewById(R.id.rdoPaycheck);
        valueEditText = (EditText) view.findViewById(R.id.txtValue);
        categorySpinner = (Spinner) view.findViewById(R.id.spnCategories);
        goButton = (Button) view.findViewById(R.id.btnGo);

        //Set Spinner Adapter and other event listeners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        goButton.setOnClickListener(this);
        paymentRadioGroup.setOnCheckedChangeListener(this);
        valueEditText.setOnEditorActionListener(this);

        return view;
}

    @Override
    public void onResume()
    {
        super.onResume();

        //Reset widgets
        valueEditText.setText(prefs.getString("pay_value", ""));
        if (!prefs.getBoolean("payment_checked", true)) { paycheckRadioButton.setSelected(true); }
        else {categorySpinner.setSelection(prefs.getInt("category", 0), true);}
    }

    @Override
    public void onPause()
    {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("pay_value", valueEditText.getText().toString());
        editor.putInt("category", categorySpinner.getSelectedItemPosition());
        editor.putBoolean("payment_checked", PaymentChecked);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onClick(View view) {

        //Gets value from EditText and gives error message if non-numeric
        String valueStr = valueEditText.getText().toString();
        try
        {
            if (valueStr != null && valueStr.length() > 0)
                { value = Double.parseDouble(valueStr); }
            else {throw new Exception();}
        }
        catch(Exception e) { Toast.makeText(getActivity(), "Please enter a numeric value", Toast.LENGTH_SHORT).show(); }

        //Records the current date and spinner value
        String currentDate = Long.toString(new Date().getTime());
        String category = categorySpinner.getSelectedItem().toString();
        double newValue;
        boolean successful = false;

        double MortgageDB = categories.get(0);
        double GroceriesDB = categories.get(1);
        double GasDB = categories.get(2);
        double SpendingDB = categories.get(3);

        //Payments are recorded here
        if (PaymentChecked)
        {
            switch(category)
            {
                case "Monthly Mortgage Payment":

                    //If the spinner is on Mortgage, the value will be subtracted from the mortgage value
                    //This new balance is validated and recorded in the categories database
                    newValue = MortgageDB - value;
                    if (balanceValidator(Double.toString(newValue)))
                    {
                        successful = true;
                        Category newCategory = new Category(newValue, GroceriesDB, GasDB, SpendingDB);
                        cDB.updateCategories(newCategory);
                    }
                    break;
                case "Groceries":
                    newValue = GroceriesDB - value;
                    if (balanceValidator(Double.toString(newValue)))
                    {
                        successful = true;
                        Category newCategory = new Category(MortgageDB, newValue, GasDB, SpendingDB);
                        cDB.updateCategories(newCategory);
                    }
                    break;
                case "Gas":
                    newValue = GasDB - value;
                    if (balanceValidator(Double.toString(newValue)))
                    {
                        successful = true;
                        Category newCategory = new Category(MortgageDB, GroceriesDB, newValue, SpendingDB);
                        cDB.updateCategories(newCategory);
                    }
                    break;
                case "Spending":
                    newValue = SpendingDB - value;
                    if (balanceValidator(Double.toString(newValue)))
                    {
                        successful = true;
                        Category newCategory = new Category(MortgageDB, GroceriesDB, GasDB, newValue);
                        cDB.updateCategories(newCategory);
                    }
                    break;
            }
        }

        //Paychecks are recorded here
        else
        {
            //Custom percentages are loaded from preferences
            int[] percentages = getFormattedPercentages();
            category = "Paycheck";
            successful = true;
            //Values are set to a new category object and added to the current DB values
            Category addCategory = new Category(
                    value * (percentages[0] / 100.0),
                    value * (percentages[1] / 100.0),
                    value * (percentages[2] / 100.0),
                    value * (percentages[3] / 100.0));
            cDB.addToCategories(addCategory);
        }

        //If the operation was successful, the entry is finally inserted into the entries DB
        if (successful)
        {
            BudgetEntry newEntry = new BudgetEntry(value, category, currentDate);
            beDB.insertEntry(newEntry);
        }
        else { Toast.makeText(getActivity(), "Operation Not Successful: Payment exceeds category value.", Toast.LENGTH_SHORT).show(); }

        //Value entered by the user is cleared
        valueEditText.setText("");
        getActivity().finish();
    }


    public boolean balanceValidator(String valueStr)
    {
        //Validates that a payment doesn't exceed the current value of a category...
        //...assuming that the "Allow Negative Balance" preference is off
        Double value = Double.parseDouble(valueStr);
        boolean allowNegativeBalance = prefs.getBoolean("pref_negative_balance", true);
        if (allowNegativeBalance) {return true;}
        else if (!allowNegativeBalance && value >= 0) {return true;}
        else {return false;}
    }

    public int[] getFormattedPercentages()
    {
        //Formats and returns the string from the "Custom Budget Percentage" preference
        String[] percentages = prefs.getString("pref_custom_percentages", "60, 20, 10, 10")
                .split(", ");
        int[] values = new int[4];

        for (int i = 0; i < 4; i++) { values[i] = Integer.parseInt(percentages[i]); }
        return values;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        //Shows or hides the category spinner depending on which radio button is selected
        switch(i)
        {
            case R.id.rdoPaycheck:
                PaymentChecked = false;
                categorySpinner.setVisibility(View.GONE);
                break;
            case R.id.rdoPayment:
                PaymentChecked = true;
                categorySpinner.setVisibility(View.VISIBLE);
                break;
        }
    }

    //Hides keyboard
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) { return false; }
}
