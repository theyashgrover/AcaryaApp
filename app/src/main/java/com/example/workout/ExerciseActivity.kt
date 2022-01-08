package com.example.workout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.dialog_custom_back_confirmation.*
import java.util.*

class ExerciseActivity : AppCompatActivity() , TextToSpeech.OnInitListener {
    //for timer that comes before starting the first exercise and after finishing every exercise..
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var restTimerDuration : Long = 7
    //for timer that runs while you are doing your exercise and runs for every exercise..
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTimerDuration: Long = 15 //to prevent hardcoding of time of the exercise timer , Now we can easily alter this later in the application

    //declaring the exercise list we created using ExerciseModel and Constant Classes
    private var exerciseList:ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    //an object which is imp. to declare in order to implement the text to speech functionality..
    private var tts: TextToSpeech? = null

    //an object which is imp. to  declare in order to implement the media player that runs before starting each exercise..
    private var player : MediaPlayer? = null

    //creating an object for our custom adapter of recycler view
    private var exerciseAdapter : ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        //setting up the action bar we created..
        setSupportActionBar(toolbar_exercise_activity)
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener {
            customDialogForBackButton() //instead of onBackPressed() , using this function will launch the dialog box instead
        }

        tts = TextToSpeech(this , this)

        exerciseList = Constants.defaultExerciseList() //calling the list of exercises we created
        setupRestView()

        //calling the recycler view function to set up the recycler view at the bottom
        setupExerciseStatusRecyclerView()
    }

    override fun onDestroy() {

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        //once the text is spoken we need to stop the tts functionality..
        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if (player != null){
            player!!.stop()
        }

        super.onDestroy()
    }

    //implementing the timer functionality and creating an Instance of the CountDownTimer class
    private fun setRestProgressBar() {
        progressBar.progress = restProgress //setting progress to 0
        restTimer = object : CountDownTimer(restTimerDuration*1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = restTimerDuration.toInt() - restProgress
                tvTimer.text = (restTimerDuration.toInt() - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setupRestExView()
            }
        }.start()
    }
    private fun setExerciseProgressBar() {
        progressBarExercise.progress = exerciseProgress //setting progress to 0
        exerciseTimer = object : CountDownTimer(exerciseTimerDuration*1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBarExercise.progress = exerciseTimerDuration.toInt() - exerciseProgress
                tvExerciseTimer.text = (exerciseTimerDuration.toInt() - exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition < exerciseList?.size!!- 1){
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)

                    setupRestView()
                }else{
                    finish()
                    val intent = Intent(this@ExerciseActivity , FinishActivity()::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

        private fun setupRestView() {


            try {
                player = MediaPlayer.create(applicationContext , R.raw.nanii)
                player!!.isLooping = false //wont loop the sound over and over again
                player!!.start()
            }catch(e:Exception){
                e.printStackTrace()
            }


            llRestView.visibility = View.VISIBLE
            llExerciseView.visibility = View.GONE

            if (restTimer != null) {
                restTimer!!.cancel()
                restProgress = 0
            }

            tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition+1].getName()

            setRestProgressBar()

        }
        private fun setupRestExView() {

            llRestView.visibility = View.GONE
            llExerciseView.visibility = View.VISIBLE

            if (exerciseTimer != null) {
                exerciseTimer!!.cancel()
                exerciseProgress = 0
            }

            speakOut(exerciseList!![currentExercisePosition].getName())

            setExerciseProgressBar()

            ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage()) //.getImage is a getter that we created , It returns an Image..
            tvExerciseName.text = exerciseList!![currentExercisePosition].getName()
        }

    override fun onInit(status: Int) {
        //to check if we can access text to speech
        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS" , "The language specified is not supported !")
            }
        }else{
            Log.e("TTS" , "Initialization Failed")
        }
    }

    private fun speakOut(text : String){
        tts!!.speak(text , TextToSpeech.QUEUE_FLUSH , null , "")
    }

    //function for setting up recycler view
    fun setupExerciseStatusRecyclerView(){
            rvExerciseStatus.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
            exerciseAdapter = ExerciseStatusAdapter(exerciseList!! , this)
            rvExerciseStatus.adapter = exerciseAdapter
    }
    private fun customDialogForBackButton(){
        val customDialog = Dialog(this)

        customDialog.setContentView(R.layout.dialog_custom_back_confirmation)
        customDialog.tvYes.setOnClickListener {
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

}
