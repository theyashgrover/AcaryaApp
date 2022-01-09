package com.example.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.logging.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN ,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val stb = AnimationUtils.loadAnimation(this , R.anim.stb)

        splash_layout.setOnClickListener {
                splash_layout.startAnimation(stb)
        }

        android.os.Handler().postDelayed({
          startActivity(Intent(this , MainActivity::class.java))
            finish()
        }, 2500)
    }
}