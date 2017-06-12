package com.example.libbyalicia.watermindr;

/**
 * Created by libbyjennings on 5/06/17.
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
class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "watermindr.db"; //set database name
    private static final String TABLE_PLANT_GUIDE = "plantguides"; //set table

    //set table columns
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "plantName";
    public static final String COLUMN_WATER_FREQUENCY = "waterFrequency";
    public static final String COLUMN_OPTIMAL_SEASON = "optimalSeason";

    public List<PlantGuide> plantGuideList = new ArrayList<PlantGuide>(); //intialise goals list

    //Set up constructor
    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PLANT_GUIDE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + COLUMN_WATER_FREQUENCY + " TEXT," + COLUMN_OPTIMAL_SEASON + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANT_GUIDE);
        onCreate(db);
    }

    //Add goal to database
    public void addPlantGuide(PlantGuide pGuide) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, pGuide.getName()); //add the plants name
        values.put(COLUMN_WATER_FREQUENCY, pGuide.getWaterFrequency()); //add the plants water frequency 1,3,5 or 7
        values.put(COLUMN_OPTIMAL_SEASON, pGuide.getOptimalSeason()); //add the plants optimal season

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PLANT_GUIDE, null, values); //insert values into db table
        db.close(); //close db
    }

    //Add goals from database to a list to display in the listview
    public List<PlantGuide> findPlantGuides() {

        String selectQuery = "SELECT  * FROM " + TABLE_PLANT_GUIDE; //get all plant guides
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        PlantGuide pGuide = new PlantGuide();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                pGuide.setID(Integer.parseInt(cursor.getString(0))); //set id
                pGuide.setName(cursor.getString(1)); //set name
                pGuide.setWaterFrequency(Long.parseLong(cursor.getString(2))); //set watering frequency
                pGuide.setOptimalSeason(cursor.getString(3)); //set optimal season
                plantGuideList.add(new PlantGuide(pGuide.getName(), pGuide.getWaterFrequency(), pGuide.getOptimalSeason())); //add to list
                cursor.moveToNext(); //go to next plant guide
            }
        }
        return plantGuideList;

    }

    //Delete goal from database
    public boolean deletePlantGuide(String name) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_PLANT_GUIDE + " WHERE " + COLUMN_NAME + " =  \"" + name + "\""; //find and delete specific goal

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        PlantGuide pGuide = new PlantGuide();

        //delete first goal with matching id
        if (cursor.moveToFirst()) {
            pGuide.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PLANT_GUIDE, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(pGuide.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    //Delete all goals for development purposes
    public void deleteAllPlantGuides() {


        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from " + TABLE_PLANT_GUIDE); //delete all goals from table
    }

    //update goal to checked
    /*public void updateGoal(String name) {

        int value = 1;

        String strSQL = " UPDATE " + TABLE_PLANT_GUIDE + " SET " + COLUMN_VALUE + " = " + value + " WHERE " + COLUMN_NAME + " =  \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(strSQL);

    }*/

    // Getting All Contacts
    /*public List<PlantGuide> getAllPlantGuides() {
        List<PlantGuide> plantGuideList = new ArrayList<PlantGuide>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLANT_GUIDE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PlantGuide plantGuide = new PlantGuide();
                plantGuide.setID(Integer.parseInt(cursor.getString(0)));
                plantGuide.setName(cursor.getString(1));
                plantGuide.setOptimalSeason(cursor.getString(2));
                plantGuide.setWaterFrequency(Integer.parseInt(cursor.getString(3)));
                Log.d("Debug: ", "Finished setting");
                String name = cursor.getString(1) +"\n"+ cursor.getString(2)
                        +"\n"+ cursor.getString(3) +"\n"+ cursor.getString(4);
                AddPlantActivity.plantGuideList.add(name);
                // Adding contact to list
                plantGuideList.add(plantGuide);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return plantGuideList;
    }*/

}