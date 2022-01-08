package com.example.workout

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment=HistoryFragment()
        val secondFragment=WorkoutFragment()
        val thirdFragment=BmiFragment()

        setCurrentFragment(secondFragment)
        bottom_navigation.selectedItemId = R.id.nav_workout

        bottom_navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_history->setCurrentFragment(firstFragment)
                R.id.nav_workout->setCurrentFragment(secondFragment)
                R.id.nav_bmi->setCurrentFragment(thirdFragment)

            }
            true
        }

    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }

}