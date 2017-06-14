package com.example.libbyalicia.watermindr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import android.util.Log;

import java.util.List;

import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.TextView;

import java.util.Iterator;

//Class for displaying the study goals activity
public class NotificationView extends AppCompatActivity {

    ListView lv;
    TextView plantTextView;
    CheckBox checkBox1;
    public List<PlantsToWater> toWater = new ArrayList<PlantsToWater>(); //initialise goals list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toWater.clear(); //clear goal list just in case
        //deleteAll(); //delete all goals if neccessary for testing purposes
        showGoals(); //show goals from database

        setContentView(R.layout.notification);

        plantTextView = (TextView) findViewById(R.id.toWaterTV);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

        lv = (ListView) findViewById(R.id.listView1);

        //initialise test goals if desired
        /*goalsList.add(new Goals("test", 0));
        goalsList.add(new Goals("hello", 0));
        goalsList.add(new Goals("burger", 1));
        goalsList.add(new Goals("sausage", 1));*/

        final PlantsToWaterAdaptor adapter = new PlantsToWaterAdaptor(this, toWater);
        lv.setAdapter(adapter);
    }


    //method for showing the goals from the database
    public void showGoals() {
        PlantsToWaterDBHandler dbHandler = new PlantsToWaterDBHandler(this, null, null, 1);
        toWater = dbHandler.findPlants(); //add the foudn goals to a local array list
    }


}