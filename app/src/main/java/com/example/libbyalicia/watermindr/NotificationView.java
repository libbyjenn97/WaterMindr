package com.example.libbyalicia.watermindr;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    PlantsToWaterAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toWater.clear(); //clear goal list just in case

        PlantsToWaterDBHandler dbHandler = new PlantsToWaterDBHandler(this, null, null, 1);
        //dbHandler.deleteAllPlants();
        showGoals(); //show goals from database

        if(toWater.isEmpty()){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("Well Done!");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Looks like you've watered all of your plants for the week! " +
                            "Keep it Up!")
                    .setCancelable(false)
                    .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {

                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

        setContentView(R.layout.notification);

        plantTextView = (TextView) findViewById(R.id.toWaterTV);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

        lv = (ListView) findViewById(R.id.listView1);

        //initialise test goals if desired
        /*goalsList.add(new Goals("test", 0));
        goalsList.add(new Goals("hello", 0));
        goalsList.add(new Goals("burger", 1));
        goalsList.add(new Goals("sausage", 1));*/

        adapter = new PlantsToWaterAdaptor(this, toWater);
        lv.setAdapter(adapter);
    }


    //method for showing the goals from the database
    public void showGoals() {
        PlantsToWaterDBHandler dbHandler = new PlantsToWaterDBHandler(this, null, null, 1);
        toWater = dbHandler.findPlants(); //add the foudn goals to a local array list
    }

    public void updateAdaptor(){
        adapter.notifyDataSetChanged();
    }
}