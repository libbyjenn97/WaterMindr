package com.example.libbyalicia.watermindr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WhatsNextActivity extends AppCompatActivity {

    private GridView gridView;
    public List<PlantGuide> plantGuideList = new ArrayList<PlantGuide>(); //initialise goals list
    public List<PlantGuide> seasonalPlantsList = new ArrayList<PlantGuide>(); //initialise goals list
    public String month;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_next);

        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("MMMM");

        month = simpleDateFormat.format(date);

        Log.d("Month: ", month);


        showPlantGuides(); //show goals from database

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        //List<PlantGuide> plantGuides = dbHandler.findPlantGuides();

        for (PlantGuide pg : plantGuideList) {
            String log = "Id: "+pg.getID()+" ,Name: " + pg.getName() + " ,Water Frequency: " + pg.getWaterFrequency()
                    + " ,Optimal Season: " + pg.getOptimalSeason();
            // Writing Contacts to log
            Log.d("Name: ", log);

        }

        gridView = (GridView) findViewById(R.id.gridView1);


        final PlantGuideAdaptor adapter = new PlantGuideAdaptor(this, seasonalPlantsList);
        gridView.setAdapter(adapter);
    }

    //method for showing the goals from the database
    public void showPlantGuides() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        plantGuideList = dbHandler.findPlantGuides(); //add the foudn goals to a local array list

        seasonalPlantsList.clear(); //clear results just in case

        //Show results for summer
        if(month.equals("December") || month.equals("January") || month.equals("February" )) {
            for (PlantGuide pg : plantGuideList) {
                if (pg.getOptimalSeason().contentEquals("Summer")) {
                    seasonalPlantsList.add(pg);
                }
            }
        }

        //Show results for Autumn
        if(month.equals("March" )|| month.equals("April") || month.equals("May") ) {
            for (PlantGuide pg : plantGuideList) {
                if (pg.getOptimalSeason().contentEquals("Autumn")) {
                    seasonalPlantsList.add(pg);
                }
            }
        }

        //Show results for Winter
        if(month.equals("June") || month.equals("July") || month.equals("August") ) {
            for (PlantGuide pg : plantGuideList) {
                if (pg.getOptimalSeason().contentEquals("Winter")) {
                    seasonalPlantsList.add(pg);
                    Log.d("Added", "plant added: ");
                }
            }
        }

        //Show results for Spring
        if(month.equals("September" )|| month.equals("October") || month.equals("November") ) {
            for (PlantGuide pg : plantGuideList) {
                if (pg.getOptimalSeason().contentEquals("Spring")) {
                    seasonalPlantsList.add(pg);
                }
            }
        }
    }
}
