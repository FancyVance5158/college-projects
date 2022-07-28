package com.example.budgetapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BudgetEntry {

    //Class variables
    private int entryId;
    private double amount;
    private String category;
    private String date;

    public BudgetEntry(int entryId, double amount, String category, String date)
    {
        //Constructor with ID
        this.entryId = entryId;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public BudgetEntry(double amount, String category, String date)
    {
        //Constructor without ID
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getEntryId() { return entryId; }
    public void setEntryId(int entryId) { this.entryId = entryId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDateFormatted() { return new SimpleDateFormat("yy-MM-dd").format(new Date(Long.parseLong(date))); }

}
