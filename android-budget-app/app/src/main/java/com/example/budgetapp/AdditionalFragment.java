package com.example.budgetapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class AdditionalFragment extends Fragment
implements OnCheckedChangeListener {

    //Widget variables
    private RadioGroup paymentRadioGroup;
    private ListView historyListView;

    //Other variables and database variable
    private boolean PaymentChecked;
    private BudgetEntryDB beDB = MainFragment.getBeDB();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_additional, container, false);

        //Set widgets
        paymentRadioGroup = (RadioGroup) view.findViewById(R.id.rdoSelector);
        historyListView = (ListView) view.findViewById(R.id.lstHistory);

        //Set listeners
        paymentRadioGroup.setOnCheckedChangeListener(this);

        //Loads the table from the entries database
        loadTextView();
        return view;
    }

    public void loadTextView()
    {
        //Loads the History table with Paychecks or Payments, depending on the selected radio button
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        //Returns the list of paychecks or payments
        ArrayList<BudgetEntry> entries = beDB.getPaychecksOrPayments(PaymentChecked);

        //Most of this is voodoo magic that I copied from the textbook :)
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        for (BudgetEntry entry : entries)
        {
            String category = entry.getCategory();
            //Shortens the string for the Mortgage category
            if (category.equals("Monthly Mortgage Payment")) { category = "Mortgage"; }
            //Skips over the first entry which contains test data
            if (entry.getEntryId() == 0) { continue; }

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("date", entry.getDateFormatted());
            map.put("category", category);
            map.put("amount", formatter.format(entry.getAmount()));
            data.add(map);
        }

        int resource = R.layout.listview_entry;
        String[] from = {"date", "category", "amount"};
        int[] to = {R.id.dateTextView, R.id.categoryTextView, R.id.amountTextView};

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, resource, from, to);
        historyListView.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        //Sets variable and refreshes history table when radio button changes
        switch (i)
        {
            case R.id.rdoPaycheck:
                PaymentChecked = false;
                break;
            case R.id.rdoPayment:
                PaymentChecked = true;
                break;
        }
        loadTextView();
    }
}
