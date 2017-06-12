package com.example.libbyalicia.watermindr;

/**
 * Created by Libby Jennings & Alicia Craig on 11/06/17.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

//Class for handling the SQLite database
class MyPlantDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "watermindr2.db"; //set database name
    private static final String TABLE_MY_PLANTS = "myplants"; //set table

    //set table columns
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "plantName";
    public static final String COLUMN_WATER_FREQUENCY = "waterFrequency";
    public static final String COLUMN_OPTIMAL_SEASON = "optimalSeason";

    public List<MyPlants> myPlantsList = new ArrayList<MyPlants>(); //intialise goals list

    //Set up constructor
    public MyPlantDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_MY_PLANTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + COLUMN_WATER_FREQUENCY + " TEXT," + COLUMN_OPTIMAL_SEASON + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_PLANTS);
        onCreate(db);
    }

    //Add goal to database
    public void addPlant(MyPlants myPlants) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, myPlants.getName()); //add the plants name
        values.put(COLUMN_WATER_FREQUENCY, myPlants.getWaterFrequency()); //add the plants water frequency 1,3,5 or 7
        values.put(COLUMN_OPTIMAL_SEASON, myPlants.getOptimalSeason()); //add the plants optimal season

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_MY_PLANTS, null, values); //insert values into db table
        db.close(); //close db
    }

    //Add goals from database to a list to display in the listview
    public List<MyPlants> findMyPlants() {

        String selectQuery = "SELECT  * FROM " + TABLE_MY_PLANTS; //get all plant guides
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        MyPlants myPlants = new MyPlants();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                myPlants.setID(Integer.parseInt(cursor.getString(0))); //set id
                myPlants.setName(cursor.getString(1)); //set name
                myPlants.setWaterFrequency(Long.parseLong(cursor.getString(2))); //set watering frequency
                myPlants.setOptimalSeason(cursor.getString(3)); //set optimal season
                myPlantsList.add(new MyPlants(myPlants.getName(), myPlants.getWaterFrequency(), myPlants.getOptimalSeason())); //add to list
                cursor.moveToNext(); //go to next plant guide
            }
        }
        return myPlantsList;

    }

    //Delete goal from database
    public boolean deleteMyPlant(String name) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_MY_PLANTS + " WHERE " + COLUMN_NAME + " =  \"" + name + "\""; //find and delete specific goal

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        MyPlants pGuide = new MyPlants();

        //delete first goal with matching id
        if (cursor.moveToFirst()) {
            pGuide.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_MY_PLANTS, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(pGuide.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    //Delete all goals for development purposes
    public void deleteAllPlants() {


        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from " + TABLE_MY_PLANTS); //delete all goals from table
    }

}