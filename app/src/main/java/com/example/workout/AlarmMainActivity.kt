package com.example.workout

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_alarm_main.*
import java.util.*

class AlarmMainActivity : AppCompatActivity() {

    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_main)

        //for android 8 and above , we need to create a channel id for every notification , that is what we are going to create now :
        createNotificationChannel()

        selectTimeBtn.setOnClickListener {
            showTimePicker()
        }
        setAlarmButton.setOnClickListener {
            setAlarm()
        }
        cancelAlarmButton.setOnClickListener {
            cancelAlarm()
        }
    }

    private fun setAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this , AlarmReciever::class.java)

        pendingIntent =  PendingIntent.getBroadcast(this , 0 , intent , 0)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP , calendar.timeInMillis ,
            AlarmManager.INTERVAL_DAY , pendingIntent
        )
        Toast.makeText(this ,"Alarm Set Successfully" , Toast.LENGTH_LONG).show()
    }

    private fun cancelAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this , AlarmReciever::class.java)
        //this pending intent should exactly match the pending intent in the setAlarm function
        pendingIntent =  PendingIntent.getBroadcast(this , 0 , intent , 0)

        alarmManager.cancel(pendingIntent)

        Toast.makeText(this , "Alarm Cancelled" , Toast.LENGTH_SHORT).show()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name : CharSequence = "foxandroidReminderChannel"
            val description = "Channel For Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("foxandroid" , name , importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Next Water Alarm Time")
            .build()

        picker.show(supportFragmentManager , "foxandroid")
        picker.addOnPositiveButtonClickListener {
            if(picker.hour > 12){
                selectedTime.text =
                    String.format("%02d" , picker.hour - 12) + ":" + String.format(
                        "%02d" ,
                        picker.minute
                    ) + "PM"
            }else{
                String.format("%02d" , picker.hour) + ":" + String.format(
                    "%02d" ,
                    picker.minute
                ) + "AM"
            }
            //sending the time of calendar selected by the user to the designated Class
            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }
    }
}