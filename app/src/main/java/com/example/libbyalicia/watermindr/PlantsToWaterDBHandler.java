package com.example.libbyalicia.watermindr;

/**
 * Created by Libby Jennings and Alicia Craig on 14/06/17.
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
class PlantsToWaterDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "watermindr3.db"; //set database name
    private static final String TABLE_PLANTS_TO_WATER = "plantstowater"; //set table

    //set table columns
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "plantName";

    public List<PlantsToWater> plantToWaterList = new ArrayList<PlantsToWater>(); //intialise goals list

    //Set up constructor
    public PlantsToWaterDBHandler(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PLANTS_TO_WATER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANTS_TO_WATER);
        onCreate(db);
    }

    //Add goal to database
    public void addPlant(PlantsToWater toWater) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, toWater.getName()); //add the plants name

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PLANTS_TO_WATER, null, values); //insert values into db table
        db.close(); //close db
    }

    //Add goals from database to a list to display in the listview
    public List<PlantsToWater> findPlants() {

        String selectQuery = "SELECT  * FROM " + TABLE_PLANTS_TO_WATER; //get all plant guides
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        PlantsToWater toWater = new PlantsToWater();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                toWater.setID(Integer.parseInt(cursor.getString(0))); //set id
                toWater.setName(cursor.getString(1)); //set name
                plantToWaterList.add(new PlantsToWater(toWater.getName())); //add to list
                cursor.moveToNext(); //go to next plant guide
            }
        }
        return plantToWaterList;

    }

    //Delete goal from database
    public boolean deleteMyPlant(String name) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_PLANTS_TO_WATER + " WHERE " + COLUMN_NAME + " =  \"" + name + "\""; //find and delete specific goal

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        PlantsToWater toWater = new PlantsToWater();

        //delete first goal with matching id
        if (cursor.moveToFirst()) {
            toWater.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PLANTS_TO_WATER, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(toWater.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    //Delete all goals for development purposes
    public void deleteAllPlants() {


        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from " + TABLE_PLANTS_TO_WATER); //delete all goals from table
    }

}
