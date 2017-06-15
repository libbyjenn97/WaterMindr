package com.example.libbyalicia.watermindr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class AddPlantActivity extends AppCompatActivity {

    private GridView gridView;
    public List<PlantGuide> plantGuideList = new ArrayList<PlantGuide>(); //initialise goals list
    public PlantGuideAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);


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


        adapter = new PlantGuideAdaptor(this, plantGuideList);
        gridView.setAdapter(adapter);

        /*gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    //method for showing the goals from the database
    public void showPlantGuides() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        plantGuideList = dbHandler.findPlantGuides(); //add the foudn goals to a local array list
    }

}
