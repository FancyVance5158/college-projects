package com.example.budgetapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class CategoryDB {

    //Class variables and constants
    public static final String DB_NAME = "budgetapp_category.db";
    public static final int    DB_VERSION = 1;

    public static final String CATEGORY_TABLE = "category";

    public static final String CATEGORY_ID = "_id";
    public static final int    CATEGORY_ID_COL = 0;

    public static final String CATEGORY_MORTGAGE = "mortgage";
    public static final int    CATEGORY_MORTGAGE_COL = 1;

    public static final String CATEGORY_GROCERIES = "groceries";
    public static final int    CATEGORY_GROCERIES_COL = 2;

    public static final String CATEGORY_GAS = "gas";
    public static final int    CATEGORY_GAS_COL = 3;

    public static final String CATEGORY_SPENDING = "spending";
    public static final int    CATEGORY_SPENDING_COL = 4;

    public static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + CATEGORY_TABLE + " (" +
                    CATEGORY_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CATEGORY_MORTGAGE   + " REAL NOT NULL, " +
                    CATEGORY_GROCERIES  + " REAL NOT NULL, " +
                    CATEGORY_GAS        + " REAL NOT NULL, " +
                    CATEGORY_SPENDING   + " REAL NOT NULL)";

    public static final String DROP_CATEGORY_TABLE =
            "DROP TABLE IF EXISTS " + CATEGORY_TABLE;

    private SQLiteDatabase db;
    private DBHelper dbHelper;


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_CATEGORY_TABLE);
            String defaultString = "INSERT INTO category VALUES (0, 0.0, 0.0, 0.0, 0.0)";
            db.execSQL(defaultString);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("Category", "Upgrading db from version "
                    + oldVersion + "to " + newVersion);
            db.execSQL(CategoryDB.DROP_CATEGORY_TABLE);
            onCreate(db);
        }
    }

    public Category getCategories()
    {
        //Gets a Category object with values for each of the four categories
        this.openReadableDB();

        //Only gets the first row
        String where = CATEGORY_ID + "= 0";
        Cursor cursor = db.query(CATEGORY_TABLE, null, where, null, null, null, null);
        cursor.moveToFirst();
        Category categories = getCategoriesFromCursor(cursor);
        if (cursor != null) { cursor.close(); }
        this.closeDB();

        return categories;
    }

    private static Category getCategoriesFromCursor(Cursor cursor)
    {
        if (cursor == null || cursor.getCount() == 0) { return null; }
        else
        {
            try
            {
                Category category = new Category(
                        cursor.getInt(CATEGORY_ID_COL),
                        cursor.getDouble(CATEGORY_MORTGAGE_COL),
                        cursor.getDouble(CATEGORY_GROCERIES_COL),
                        cursor.getDouble(CATEGORY_GAS_COL),
                        cursor.getDouble(CATEGORY_SPENDING_COL));
                return category;
            }
            catch(Exception e)
            {
                return null;
            }
        }
    }

    public long updateCategories(Category category)
    {
        //Takes in Category object with new values and sets them in DB
        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_MORTGAGE, category.getMortgage());
        cv.put(CATEGORY_GROCERIES, category.getGroceries());
        cv.put(CATEGORY_GAS, category.getGas());
        cv.put(CATEGORY_SPENDING, category.getSpending());

        //Again, only the first row
        String where = CATEGORY_ID + "= 0";
        this.openWriteableDB();
        long rowID = db.update(CATEGORY_TABLE, cv, where, null);
        this.closeDB();
        return rowID;
    }

    public void addToCategories(Category category)
    {
        //Takes a Category object with new values, and adds them to the existing database values
        ArrayList<Double> oldCategories = getCategoryValues();
        Category newCategories = new Category();

        //Each category is added together and set to a new Category object
        newCategories.setMortgage(oldCategories.get(0) + category.getMortgage());
        newCategories.setGroceries(oldCategories.get(1) + category.getGroceries());
        newCategories.setGas(oldCategories.get(2) + category.getGas());
        newCategories.setSpending(oldCategories.get(3) + category.getSpending());

        //The database is updated with the new values
        updateCategories(newCategories);
    }

    public ArrayList<Double> getCategoryValues()
    {
        //Returns a list with the actual values of each category
        ArrayList<Double> values = new ArrayList<Double>(4);
        Category categories = getCategories();
        values.add(0, categories.getMortgage());
        values.add(1, categories.getGroceries());
        values.add(2, categories.getGas());
        values.add(3, categories.getSpending());
        return values;
    }

    public CategoryDB(Context context)
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

