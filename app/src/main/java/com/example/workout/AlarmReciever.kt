package com.example.workout

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //creating the intent for our notification and a pending Intent for the same
        val i = Intent(context , DestinationAlarmActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context,0,i,0)


        //to build the notification with certain attributes :
        val builder = NotificationCompat.Builder(context!! , "foxandroid")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Alarm app is working")
            .setContentText("Bete Mojj Krdii")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        //to show the notification :
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123,builder.build())
    }
}