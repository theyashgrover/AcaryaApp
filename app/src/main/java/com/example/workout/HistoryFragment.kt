package com.example.workout

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.workout.databinding.FragmentHistoryBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_alarm_main.*
import kotlinx.android.synthetic.main.activity_history.*
import java.util.*

class HistoryFragment : Fragment(){

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate the layout and bind to the _binding
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        val dbHandler = SqliteOpenHelper(requireContext() , null)
        val allCompletedDatesList = dbHandler.getAllCompletedDatesList() //storing the array list

        if(allCompletedDatesList.size > 0){
            binding.tvHistory.visibility = View.VISIBLE
            binding.rvHistory.visibility = View.VISIBLE
            binding.tvNoDataAvailable.visibility = View.GONE

            binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
            val historyAdapter = HistoryAdapter(requireContext() , allCompletedDatesList)
            binding.rvHistory.adapter = historyAdapter
        }else{
            binding.tvHistory.visibility = View.GONE
            binding.rvHistory.visibility = View.GONE
            binding.tvNoDataAvailable.visibility = View.VISIBLE
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}