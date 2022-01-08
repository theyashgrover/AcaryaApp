package com.example.workout

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bmiactivity.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibileView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)

        setSupportActionBar(toolbar_bmi_activity)

        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "CALCULATE BMI"
        }
        toolbar_bmi_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        rgUnits.setOnCheckedChangeListener { radiogroup : RadioGroup, checkedId : Int ->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUsUnitsView()
            }
        }

        btnCalculateUnits.setOnClickListener {
            if(currentVisibileView == METRIC_UNITS_VIEW){
                if(validateMetricUnits()){
                    val heightValue : Float = etMetricUnitHeight.text.toString().toFloat() / 100
                    val weightValue : Float = etMetricUnitWeight.text.toString().toFloat()
                    val bmi = weightValue / (heightValue * heightValue)
                    displayBMIResult(bmi)
                }else{
                    Toast.makeText(this@BMIActivity , "Please Enter Valid Values" , Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(this@BMIActivity , "Please Enter Valid Values" , Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun makeVisibleUsUnitsView(){
        currentVisibileView = US_UNITS_VIEW

        tilMetricUnitWeight.visibility = View.GONE
        tilMetricUnitHeight.visibility = View.GONE

        etUSUnitHeightFeet.text!!.clear()
        etUSUnitWeight.text!!.clear()
        etUSUnitHeightInches.text!!.clear()


        tilUSUnitWeight.visibility = View.VISIBLE
        llUSUnitsHeight.visibility = View.VISIBLE

        llDisplayBMIResult.visibility = View.INVISIBLE
    }


    private fun makeVisibleMetricUnitsView(){
        currentVisibileView = METRIC_UNITS_VIEW
        tilMetricUnitWeight.visibility = View.VISIBLE
        tilMetricUnitHeight.visibility = View.VISIBLE

        etMetricUnitWeight.text!!.clear()
        etMetricUnitHeight.text!!.clear()

        tilUSUnitWeight.visibility = View.GONE
        llUSUnitsHeight.visibility = View.GONE

        llDisplayBMIResult.visibility = View.INVISIBLE
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
        llDisplayBMIResult.visibility = View.VISIBLE
//        tvYourBMI.visibility = View.VISIBLE
//        tvBMIValue.visibility = View.VISIBLE
//        tvBMIType.visibility = View.VISIBLE
//        tvBMIDescription.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString() //rounds off the bmi value calculated upto 2 units

        tvBMIValue.text = bmiValue
        tvBMIType.text = bmiLabel
        tvBMIDescription.text = bmiDescription

    }

    //to check if the user entered values correctly or not :
    private fun validateMetricUnits(): Boolean{
        var isValid = true

        if(etMetricUnitHeight.text.toString().isEmpty()){
            isValid=false
        }else if(etMetricUnitWeight.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

    private fun validateUsUnits(): Boolean{
        var isValid = true

        if(etUSUnitHeightFeet.text.toString().isEmpty()){
            isValid=false
        }else if(etUSUnitHeightInches.text.toString().isEmpty()){
            isValid = false
        }else if(etUSUnitWeight.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

}