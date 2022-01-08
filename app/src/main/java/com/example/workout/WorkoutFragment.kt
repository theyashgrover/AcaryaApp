package com.example.workout

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.example.workout.databinding.FragmentWorkoutBinding
import kotlinx.android.synthetic.main.fragment_workout.*

class WorkoutFragment : Fragment() {

    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // inflate the layout and bind to the _binding
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)

        //animation code:
        val ttb = AnimationUtils.loadAnimation(activity , R.anim.ttb)
        val stb = AnimationUtils.loadAnimation(activity , R.anim.stb)
        val btt = AnimationUtils.loadAnimation(activity , R.anim.btt)
        binding.textView.startAnimation(ttb)
        binding.textView2.startAnimation(ttb)
        binding.llStart.startAnimation(stb)
        binding.llAlarms.startAnimation(stb)

        binding.llStart.setOnClickListener{
            val intent = Intent(activity , ExerciseActivity::class.java) // :: is the java extension symbol , to retrieve the java class of an object  , we use it..
            startActivity(intent)
        }



        binding.llAlarms.setOnClickListener {
            val intent = Intent(activity , AlarmMainActivity::class.java)
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}