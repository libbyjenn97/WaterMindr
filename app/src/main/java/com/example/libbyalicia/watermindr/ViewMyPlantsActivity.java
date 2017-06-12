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


        gridView = (GridView) findViewById(R.id.gridView1);


        final MyPlantsAdaptor adapter = new MyPlantsAdaptor(this, myPlantsList);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void createNotification() {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, NotificationView.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Time to water your plant!")
                .setContentText("Subject").setSmallIcon(R.drawable.calendar_icon)
                .setContentIntent(pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAt10() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 30);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, pendingIntent);
    }


}
