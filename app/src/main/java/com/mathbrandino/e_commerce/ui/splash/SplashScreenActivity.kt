package com.mathbrandino.e_commerce.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mathbrandino.e_commerce.ui.list.MainActivity
import com.mathbrandino.e_commerce.R

class SplashScreenActivity : AppCompatActivity(R.layout.activity_splash_screen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper())
            .postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 2000)
    }
}