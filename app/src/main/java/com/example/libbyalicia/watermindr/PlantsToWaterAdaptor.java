package com.example.libbyalicia.watermindr;

/**
 * Created by Libby Jennings and Alicia Craig on 14/06/17.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.logging.Handler;
import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.List;

//adaptor class for loading Goal items into the checkboxes in the list view
public class PlantsToWaterAdaptor extends ArrayAdapter {

    Context context;
    List<PlantsToWater> toWater = new ArrayList<PlantsToWater>(); //initialise goal list
    static PlantsToWater thisPlant;

    public PlantsToWaterAdaptor(Context context, List<PlantsToWater> resource) {
        super(context, R.layout.plants_to_water_row, resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.toWater = resource;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.plants_to_water_row, parent, false);
        final TextView name = (TextView) convertView.findViewById(R.id.toWaterTV); //checkbox text view
        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1); //the checkbox
        name.setText(toWater.get(position).getName()); //set checkbox from goals list
        thisPlant.setName(name.toString());

        cb.setChecked(false);
        //Set goal to completed/checked when clicked
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                          @Override
                                          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                              cb.setChecked(true); //set goal as checked
                                              //goalsList.get(position).value = 1;


                                              CountDownTimer timer = new CountDownTimer(5000, 5000) {
                                                  public void onTick(long millisUntilFinished) {
                                                  }

                                                  public void onFinish() {

                                                      removeGoal(name.toString());

                                                      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                                              context);

                                                      // set title
                                                      alertDialogBuilder.setTitle("Congratulations!");

                                                      // set dialog message
                                                      alertDialogBuilder
                                                              .setMessage("Well done on watering you plant!")
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
                                              };


                                          }



                                      }
        );
        return convertView;

    }

    //method for removing a goal from the database
    public void removeGoal(String thisPlant) {

        PlantsToWaterDBHandler dbHandler = new PlantsToWaterDBHandler(context, null, null, 1);
        boolean result = dbHandler.deleteMyPlant(thisPlant.toString()); //delete goal

        //show if goal was deleted or not
        if (result) {
            Log.d("my tag", "deleted"); //confirm deletion
        } else
            Log.d("my tag", "not found"); //confirm not found
    }

}
