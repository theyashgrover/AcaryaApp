package com.example.workout

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //animation code:
        val ttb = AnimationUtils.loadAnimation(this , R.anim.ttb)
        val stb = AnimationUtils.loadAnimation(this , R.anim.stb)
        val btt = AnimationUtils.loadAnimation(this , R.anim.btt)
        textView.startAnimation(ttb)
        textView2.startAnimation(ttb)
        llStart.startAnimation(stb)
        llBMI.startAnimation(stb)
        llAlarms.startAnimation(stb)
        llHistory.startAnimation(stb)

        llStart.setOnClickListener{
            val intent = Intent(this , ExerciseActivity::class.java) // :: is the java extension symbol , to retrieve the java class of an object  , we use it..
            startActivity(intent)
        }

        llBMI.setOnClickListener {
            val intent = Intent(this , BMIActivity::class.java)
            startActivity(intent)
        }
        llHistory.setOnClickListener {
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }
        llAlarms.setOnClickListener {
            val intent = Intent(this , AlarmMainActivity::class.java)
            startActivity(intent)
        }

    }

}