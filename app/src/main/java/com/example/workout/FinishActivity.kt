package com.example.workout

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        setSupportActionBar(toolbar_finish_activity)
        val actionbar = supportActionBar //actionbar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)//set back button
        }
        toolbar_finish_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        btnFinish.setOnClickListener {
            finish()
        }
        addDateToDatabase() //calling the function to save the date to our Database
    }
     private fun addDateToDatabase(){
         val calendar = Calendar.getInstance() //import java vala calendar hamesha , cuz I am familiar with that
         val dateTime = calendar.time
         Log.e("Date: ","" + dateTime)

         val sdf = SimpleDateFormat("dd MM yyyy HH:mm:ss",Locale.getDefault())
         val date = sdf.format(dateTime)
         Log.e("Formatted Date : ", "" + date)

         val dbHandler =  SqliteOpenHelper(this , null)
         dbHandler.addDate(date)
         Log.e("Date: ","Added")
     }

}
