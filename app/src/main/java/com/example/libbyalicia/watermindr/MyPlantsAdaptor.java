package com.example.libbyalicia.watermindr;

/**
 * Created by libbyjennings on 11/06/17.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MyPlantsAdaptor extends ArrayAdapter {
    Context context;
    List<MyPlants> myPlantsList = new ArrayList<MyPlants>(); //initialise goal list
    public static PlantsToWater thisPlant = new PlantsToWater();

    public MyPlantsAdaptor(Context context, List<MyPlants> resource) {
        super(context, R.layout.my_plants_row, resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.myPlantsList = resource;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.my_plants_row, parent, false);

        final TextView name = (TextView) convertView.findViewById(R.id.plantNameTV); //checkbox text view
        name.setText(myPlantsList.get(position).getName()); //set checkbox from goals list

        Long waterFrequency = myPlantsList.get(position).getWaterFrequency();
        String waterDays = "";

        if(waterFrequency == 1){

            waterDays = "Once a week";
        }
        else if (waterFrequency == 3){

            waterDays = "Every Second Day";
        }

        else if (waterFrequency == 7){

            waterDays = "Every Day";
        }

        String waterFrequencyString = Long.toString(waterFrequency);
        TextView frequency = (TextView) convertView.findViewById(R.id.WaterFrequencyTV); //checkbox text view
        //frequency.append(waterFrequencyString); //set checkbox from goals list
        frequency.append(waterDays);

        TextView season = (TextView) convertView.findViewById(R.id.OptimalSeasonTV); //checkbox text view
        season.append(myPlantsList.get(position).getOptimalSeason()); //set checkbox from goals list

        ToggleButton toggle = (ToggleButton) convertView.findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Long waterFrequency = myPlantsList.get(position).getWaterFrequency();
                if (isChecked) {

                    //set notification once a week
                    if(waterFrequency == 1){

                        ((ViewMyPlantsActivity)context).startOnceWeek();
                        ((ViewMyPlantsActivity)context).start(); //call for testing
                    }
                    //set notification every second day
                    else if (waterFrequency == 3){

                        ((ViewMyPlantsActivity)context).startEverySecondDay();
                        ((ViewMyPlantsActivity)context).start(); //call for testing
                    }

                    //set notification everyday
                    else if (waterFrequency == 7){

                        ((ViewMyPlantsActivity)context).startEveryDay();
                        ((ViewMyPlantsActivity)context).start(); //call for testing

                    }

                    String plantName = myPlantsList.get(position).getName();
                    thisPlant.setName(plantName);

                } else {
                    ((ViewMyPlantsActivity)context).cancel();

                }
            }
        });

        Button deletePlantBtn = (Button) convertView.findViewById(R.id.deletePlantBtn);
        deletePlantBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                MyPlantDBHandler dbHandler = new MyPlantDBHandler(context, null, null, 1);

                String plantName = myPlantsList.get(position).getName();

                dbHandler.deleteMyPlant(plantName);
                myPlantsList.remove(position);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Plant Deleted");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Your plant has been deleted.")
                        .setCancelable(false)
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                ((ViewMyPlantsActivity)context).updateAdaptor();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        return convertView;

    }

    public static PlantsToWater getPlantName() {
        return thisPlant;
    }
}