package com.example.libbyalicia.watermindr;

/**
 * Created by Libby Jennings & Alicia Craig on 5/06/17.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;


import java.util.ArrayList;
import java.util.List;

public class PlantGuideAdaptor extends ArrayAdapter {
    Context context;
    List<PlantGuide> plantGuideList = new ArrayList<PlantGuide>(); //initialise goal list

    public PlantGuideAdaptor(Context context, List<PlantGuide> resource) {
        super(context, R.layout.row, resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.plantGuideList = resource;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);

        TextView name = (TextView) convertView.findViewById(R.id.plantNameTV); //checkbox text view
        name.setText(plantGuideList.get(position).getName()); //set checkbox from goals list

        Long waterFrequency = plantGuideList.get(position).getWaterFrequency();
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
        season.append(plantGuideList.get(position).getOptimalSeason()); //set checkbox from goals list

        Button addPlantBtn = (Button) convertView.findViewById(R.id.addPlantBtn);

        addPlantBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                MyPlantDBHandler dbHandler = new MyPlantDBHandler(context, null, null, 1);

                // Inserting plant guides
                Log.d("Insert: ", "Inserting ..");

                String plantName = plantGuideList.get(position).getName();
                Long waterFreq = plantGuideList.get(position).getWaterFrequency();
                String opSeason = plantGuideList.get(position).getOptimalSeason();

                dbHandler.addPlant(new MyPlants(plantName, waterFreq, opSeason));

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Plant Added");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Your plant has been added. Click 'OK' to add another plant")
                        .setCancelable(false)
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
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
}

