package com.example.libbyalicia.watermindr;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.PendingIntent;
import android.app.Notification;
import android.widget.ToggleButton;
import android.content.DialogInterface.OnClickListener;
import android.app.AlarmManager;
import java.util.Calendar;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

public class ViewMyPlantsActivity extends AppCompatActivity {

    private GridView gridView;
    public List<MyPlants> myPlantsList = new ArrayList<MyPlants>(); //initialise goals list
    private PendingIntent pendingIntent;
    public MyPlantsAdaptor adapter;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);


        MyPlantDBHandler dbHandler = new MyPlantDBHandler(this, null, null, 1);
        //dbHandler.deleteAllPlants();
        showPlantGuides(); //show goals from database


        // Reading all contacts
        Log.d("Reading: ", "Reading all of your plants..");
        //List<PlantGuide> plantGuides = dbHandler.findPlantGuides();

        for (MyPlants pg : myPlantsList) {
            String log = "Id: "+pg.getID()+" ,Name: " + pg.getName() + " ,Water Frequency: " + pg.getWaterFrequency()
                    + " ,Optimal Season: " + pg.getOptimalSeason();
            // Writing Contacts to log
            Log.d("Name: ", log);

        }

        if(myPlantsList.isEmpty()){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("No Plants");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Woops! You don't have any plants yet. \n" +
                            "Add one from the 'Add Plant' mneu")
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


        gridView = (GridView) findViewById(R.id.gridView1);


        adapter = new MyPlantsAdaptor(this, myPlantsList);
        gridView.setAdapter(adapter);

          /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(ViewMyPlantsActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(ViewMyPlantsActivity.this, 0, alarmIntent, 0);

    }

    //method for showing the goals from the database
    public void showPlantGuides() {
        MyPlantDBHandler dbHandler = new MyPlantDBHandler(this, null, null, 1);
        myPlantsList = dbHandler.findMyPlants(); //add the foudn goals to a local array list
    }

    //call this function to set notification every minute as a test
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 5000;

        //manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 1000 * 60, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    //call this function to set notification for everyday
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void startEveryDay() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 86400000; //no of ms in a day

        //manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    //call this function to set notification for every second day
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void startEverySecondDay() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 172800000; //no of ms in 2 days

        //manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    //call this function to set notification for once a week starting today
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void startOnceWeek() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 604800000; //no of ms in a week

        //manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Notification Canceled", Toast.LENGTH_SHORT).show();
    }

    /*public void startAt10() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;*/

        /* Set the alarm to start at 10:30 AM */
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 32);
        calendar.set(Calendar.SECOND, 00);

        Toast.makeText(this, "Notification Set", Toast.LENGTH_SHORT).show();*/

        /* Repeating on every 20 minutes interval */
        /*manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, pendingIntent);*/

        /*PendingIntent pi = PendingIntent.getService(this, 0,
                new Intent(this, ViewMyPlantsActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pi);
    }

    //schedules the alarm to fire everyday at 12pm (end of 11.59)
    public void scheduleEveryday() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;*/

        /* Set the alarm to start at 10:30 AM */
        /*Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 26);

        Toast.makeText(this, "Notification Set", Toast.LENGTH_SHORT).show();*/

        /* Repeating on every 20 minutes interval */
        /*manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, pendingIntent);*/

        /*Intent intent1 = new Intent(ViewMyPlantsActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ViewMyPlantsActivity.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) ViewMyPlantsActivity.this.getSystemService(ViewMyPlantsActivity.this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }*/

    public void updateAdaptor(){
        adapter.notifyDataSetChanged();
        gridView.invalidateViews();
        gridView.setAdapter(adapter);
    }
}
