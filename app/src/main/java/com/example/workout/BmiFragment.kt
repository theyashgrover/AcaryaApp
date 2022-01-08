package com.example.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import com.example.workout.databinding.FragmentBmiBinding
import com.example.workout.databinding.FragmentWorkoutBinding
import kotlinx.android.synthetic.main.activity_bmiactivity.*
import java.math.BigDecimal
import java.math.RoundingMode

class BmiFragment : Fragment() {


    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibileView: String = BmiFragment.METRIC_UNITS_VIEW

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate the layout and bind to the _binding
        _binding = FragmentBmiBinding.inflate(inflater, container, false)

        makeVisibleMetricUnitsView()

        binding.rgUnits.setOnCheckedChangeListener { radiogroup : RadioGroup, checkedId : Int ->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUsUnitsView()
            }
        }

        binding.btnCalculateUnits.setOnClickListener {
            if(currentVisibileView == BmiFragment.METRIC_UNITS_VIEW){
                if(validateMetricUnits()){
                    val heightValue : Float = etMetricUnitHeight.text.toString().toFloat() / 100
                    val weightValue : Float = etMetricUnitWeight.text.toString().toFloat()
                    val bmi = weightValue / (heightValue * heightValue)
                    displayBMIResult(bmi)
                }else{
                    Toast.makeText(activity , "Please Enter Valid Values" , Toast.LENGTH_SHORT).show()
                }
            }else{
                if(validateUsUnits()){
                    val usUnitHeightValueFeet: String = etUSUnitHeightFeet.text.toString()
                    val usUnitHeightValueInches: String = etUSUnitHeightInches.text.toString()
                    val usUnitWeightValue: Float = etUSUnitWeight.text.toString().toFloat()

                    val heightValue = usUnitHeightValueInches.toFloat() +  usUnitHeightValueFeet.toFloat() * 12

                    val bmi = 703 * (usUnitWeightValue / (heightValue * heightValue))
                    displayBMIResult(bmi)
                }else{
                    Toast.makeText(activity , "Please Enter Valid Values" , Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    private fun makeVisibleUsUnitsView(){
        currentVisibileView = BmiFragment.US_UNITS_VIEW

        binding.tilMetricUnitWeight.visibility = View.GONE
        binding.tilMetricUnitHeight.visibility = View.GONE

        binding.etUSUnitHeightFeet.text!!.clear()
        binding.etUSUnitWeight.text!!.clear()
        binding.etUSUnitHeightInches.text!!.clear()


        binding.tilUSUnitWeight.visibility = View.VISIBLE
        binding.llUSUnitsHeight.visibility = View.VISIBLE

        binding.llDisplayBMIResult.visibility = View.INVISIBLE
    }


    private fun makeVisibleMetricUnitsView(){
        currentVisibileView = BmiFragment.METRIC_UNITS_VIEW
        binding.tilMetricUnitWeight.visibility = View.VISIBLE
        binding.tilMetricUnitHeight.visibility = View.VISIBLE

        binding.etMetricUnitWeight.text!!.clear()
        binding.etMetricUnitHeight.text!!.clear()

        binding.tilUSUnitWeight.visibility = View.GONE
        binding.llUSUnitsHeight.visibility = View.GONE

        binding.llDisplayBMIResult.visibility = View.INVISIBLE
    }

    //to display BMI Result
    private fun displayBMIResult(bmi: Float){
        val bmiLabel : String
        val bmiDescription :String

        if(bmi.compareTo(15f) <= 0){
            bmiLabel = "Very Severely Underweight"
            bmiDescription = "Oops ! You really need to take care of your diet better ! Eat More !"
        }else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <=0){
            bmiLabel = "Severely Underweight"
            bmiDescription = "Oops ! You really need to take care of your diet better ! Eat More !"
        }else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops ! You really need to take care of your diet better ! Eat More !"
        }else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0){
            bmiLabel = "Healthy"
            bmiDescription = "Congratulations !! , You are in a good shape !"
        }else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f)<=0){
            bmiLabel = "OverWeight"
            bmiDescription = "Oops ! You really need to take care of yourself better ! Workout More and eat healthy only!"
        }else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f)<=0){
            bmiLabel = "Obese Class || Moderately Obese"
            bmiDescription = "Oops ! You really need to take care of yourself better ! Workout More and eat healthy only!"
        }else{
            bmiLabel = "Obese Class || Severely Obese"
            bmiDescription = "OMG ! You really need to take care of yourself way better ! Workout More and eat healthy only! , You are in a dangerous condition ! Act Now!"
        }
        binding.llDisplayBMIResult.visibility = View.VISIBLE
//        tvYourBMI.visibility = View.VISIBLE
//        tvBMIValue.visibility = View.VISIBLE
//        tvBMIType.visibility = View.VISIBLE
//        tvBMIDescription.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString() //rounds off the bmi value calculated upto 2 units

        binding.tvBMIValue.text = bmiValue
        binding.tvBMIType.text = bmiLabel
        binding.tvBMIDescription.text = bmiDescription

    }

    //to check if the user entered values correctly or not :
    private fun validateMetricUnits(): Boolean{
        var isValid = true

        if(binding.etMetricUnitHeight.text.toString().isEmpty()){
            isValid=false
        }else if(binding.etMetricUnitWeight.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

    private fun validateUsUnits(): Boolean{
        var isValid = true

        if(binding.etUSUnitHeightFeet.text.toString().isEmpty()){
            isValid=false
        }else if(binding.etUSUnitHeightInches.text.toString().isEmpty()){
            isValid = false
        }else if(binding.etUSUnitWeight.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}