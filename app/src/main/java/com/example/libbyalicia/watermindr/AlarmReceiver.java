package com.example.libbyalicia.watermindr;

/**
 * Created by libbyjennings on 11/06/17.
 */


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;
import android.content.Context.*;

public class AlarmReceiver extends BroadcastReceiver {


    public AlarmReceiver() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void createNotification(Context theContext) {
        Log.d("Debug: ", theContext.toString());
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(theContext, NotificationView.class);
        PendingIntent pIntent = PendingIntent.getActivity(theContext, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(theContext)
                .setContentTitle("Time to water your plant!")
                .setContentText("Subject").setSmallIcon(R.drawable.calendar_icon)
                .setContentIntent(pIntent).build();
        NotificationManager notificationManager = (NotificationManager) theContext.getSystemService(Context.NOTIFICATION_SERVICE);

        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {

        //this.context = context;
        // For our recurring task, we'll just display a message
        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        this.createNotification(context);

    }
}