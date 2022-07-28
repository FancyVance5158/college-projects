package com.example.budgetapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BudgetEntryDB {

    //Class variables and constants
    public static final String DB_NAME = "budgetapp.db";
    public static final int    DB_VERSION = 1;

    public static final String ENTRY_TABLE = "entry";

    public static final String ENTRY_ID = "_id";
    public static final int    ENTRY_ID_COL = 0;

    public static final String ENTRY_AMOUNT = "amount";
    public static final int    ENTRY_AMOUNT_COL = 1;

    public static final String ENTRY_CATEGORY = "category";
    public static final int    ENTRY_CATEGORY_COL = 2;

    public static final String ENTRY_DATE = "date";
    public static final int    ENTRY_DATE_COL = 3;

    public static final String CREATE_ENTRY_TABLE =
            "CREATE TABLE " + ENTRY_TABLE + " (" +
                    ENTRY_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ENTRY_AMOUNT   + " REAL NOT NULL, " +
                    ENTRY_CATEGORY + " TEXT NOT NULL, " +
                    ENTRY_DATE     + " TEXT NOT NULL)";

    public static final String DROP_ENTRY_TABLE =
            "DROP TABLE IF EXISTS " + ENTRY_TABLE;

    private SQLiteDatabase db;
    private DBHelper dbHelper;


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_ENTRY_TABLE);
            String defaultString = "INSERT INTO entry VALUES (0, 200.0, \"Paycheck\", 0)";
            db.execSQL(defaultString);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("Budget Entry", "Upgrading db from version "
                    + oldVersion + "to " + newVersion);
            db.execSQL(BudgetEntryDB.DROP_ENTRY_TABLE);
            onCreate(db);
        }
    }

    public ArrayList<BudgetEntry> getBudgetEntries()
    {
        //Returns a list containing each entry in the Database
        this.openReadableDB();
        Cursor cursor = db.query(ENTRY_TABLE, null, null, null, null, null, null);
        ArrayList<BudgetEntry> entries = new ArrayList<BudgetEntry>();
        while (cursor.moveToNext())
        {
            entries.add(getEntriesFromCursor(cursor));
        }
        if (cursor != null) { cursor.close(); }
        this.closeDB();

        return entries;
    }

    public ArrayList<BudgetEntry> getPaychecksOrPayments(boolean PaymentChecked)
    {
        //Returns a list of all Paycheck entries or Payment entries, depending on
        //which radio button is selected in the Payment History activity
        this.openReadableDB();

        //For a paycheck, the category must be equal to Paycheck
        String where = ENTRY_CATEGORY + " = ?";
        String[] whereArgs = {"Paycheck"};
        if (PaymentChecked)
        {
            //For a payment, the category can be equal to one of the 4 whereArgs
            for (int i = 0; i < 3; i++)
            {
                where += " OR " + ENTRY_CATEGORY + " = ?";
            }
            whereArgs = new String[]{"Monthly Mortgage Payment", "Groceries", "Gas", "Spending"};
        }
        Cursor cursor = db.query(ENTRY_TABLE, null, where, whereArgs, null, null, null);
        ArrayList<BudgetEntry> entries = new ArrayList<BudgetEntry>();
        while (cursor.moveToNext())
        {
            entries.add(getEntriesFromCursor(cursor));
        }
        if (cursor != null) { cursor.close(); }
        this.closeDB();

        return entries;
    }

    private static BudgetEntry getEntriesFromCursor(Cursor cursor)
    {
        if (cursor == null || cursor.getCount() == 0) { return null; }
        else
        {
            try
            {
                BudgetEntry entry = new BudgetEntry(
                        cursor.getInt(ENTRY_ID_COL),
                        (float) cursor.getDouble(ENTRY_AMOUNT_COL),
                        cursor.getString(ENTRY_CATEGORY_COL),
                        cursor.getString(ENTRY_DATE_COL));
                return entry;
            }
            catch(Exception e) { return null; }
        }
    }

    public long insertEntry(BudgetEntry entry)
    {
        //Inserts a new entry to the database
        long rowID = 0;
        ContentValues cv = new ContentValues();
        cv.put(ENTRY_AMOUNT, entry.getAmount());
        cv.put(ENTRY_CATEGORY, entry.getCategory());
        cv.put(ENTRY_DATE, entry.getDate());

        this.openWriteableDB();
        rowID = db.insert(ENTRY_TABLE, null, cv);
        this.closeDB();
        return rowID;
    }

    public int deleteEntry(long id)
    {
        //Deletes an entry from the database
        String where = ENTRY_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(ENTRY_TABLE, where, whereArgs);

        for (BudgetEntry entry : getBudgetEntries())
        {
            entry.setEntryId(entry.getEntryId() - rowCount);
        }
        this.closeDB();
        return rowCount;
    }

    public BudgetEntryDB(Context context)
    {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    private void openReadableDB()
    {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB()
    {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB()
    {
        if (db != null) { db.close(); }
    }
}

