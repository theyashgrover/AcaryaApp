package com.example.workout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        //setting the ActionBar on top
        setSupportActionBar(toolbar_history_activity)

        val actionbar = supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title="       EXERCISE AGAIN"
        }
        toolbar_history_activity.setOnClickListener {
         onBackPressed()
        }
        toolbar_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        getAllCompletedDates()
    }
    private fun getAllCompletedDates(){
        val dbHandler = SqliteOpenHelper(this , null)
        val allCompletedDatesList = dbHandler.getAllCompletedDatesList() //storing the array list

        if(allCompletedDatesList.size > 0){
            tvHistory.visibility = View.VISIBLE
            rvHistory.visibility = View.VISIBLE
            tvNoDataAvailable.visibility = View.GONE

            rvHistory.layoutManager = LinearLayoutManager(this)
            val historyAdapter = HistoryAdapter(this , allCompletedDatesList)
            rvHistory.adapter = historyAdapter
        }else{
            tvHistory.visibility = View.GONE
            rvHistory.visibility = View.GONE
            tvNoDataAvailable.visibility = View.VISIBLE
        }
    }
}