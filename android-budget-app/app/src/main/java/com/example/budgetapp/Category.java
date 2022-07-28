package com.example.budgetapp;

public class Category {

    //Class variables
    private int categoryId;
    private double mortgage;
    private double groceries;
    private double gas;
    private double spending;

    public Category()
    {
        //default constructor
        this.categoryId = 0;
        this.mortgage = 0.0;
        this.groceries = 0.0;
        this.gas = 0.0;
        this.spending = 0.0;
    }

    public Category(int categoryId, double mortgage, double groceries, double gas, double spending)
    {
        //Constructor with ID
        this.categoryId = categoryId;
        this.mortgage = mortgage;
        this.groceries = groceries;
        this.gas = gas;
        this.spending = spending;
    }

    public Category(double mortgage, double groceries, double gas, double spending)
    {
        //Constructor without ID
        this.mortgage = mortgage;
        this.groceries = groceries;
        this.gas = gas;
        this.spending = spending;
    }

    public int getCategoryID() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public double getMortgage() { return mortgage; }
    public void setMortgage(double mortgage) { this.mortgage = mortgage; }

    public double getGroceries() { return groceries; }
    public void setGroceries(double groceries) { this.groceries = groceries; }

    public double getGas() { return gas; }
    public void setGas(double gas) { this.gas = gas; }

    public double getSpending() { return spending; }
    public void setSpending(double spending) { this.spending = spending; }
}
