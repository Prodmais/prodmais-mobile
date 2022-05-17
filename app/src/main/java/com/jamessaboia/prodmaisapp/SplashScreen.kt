package com.jamessaboia.prodmaisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.jamessaboia.prodmaisapp.Database.SecurityPrefences
import com.jamessaboia.prodmaisapp.Model.Login

class SplashScreen : AppCompatActivity() {

    private lateinit var mSecurityPrefences: SecurityPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        mSecurityPrefences = SecurityPrefences(this)

        val token = mSecurityPrefences.getStoredString("TOKEN")
        val mobile = mSecurityPrefences.getStoredString("MOBILE")
        
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            //code
            if(token != null && token != "" && mobile != null && mobile != ""){
                Login(token, mobile.toInt())
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else{
                Login(null, null)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 2000)
    }
}