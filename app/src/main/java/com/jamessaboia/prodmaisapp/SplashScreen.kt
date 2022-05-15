package com.jamessaboia.prodmaisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.jamessaboia.prodmaisapp.Model.Login

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            //code
            if(Login.token != null){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 2000)
    }
}