package com.jamessaboia.prodmaisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.jamessaboia.prodmaisapp.Database.SecurityPrefences
import com.jamessaboia.prodmaisapp.Interface.Listeners.MainListener
import com.jamessaboia.prodmaisapp.Model.Login

class MainActivity : AppCompatActivity(), MainListener {

    private lateinit var mSecurityPrefences: SecurityPrefences
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPrefences = SecurityPrefences(this)

        navController = findNavController(R.id.fragmentContainerView)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }

    override fun goToLogin() {
        mSecurityPrefences.storeString("TOKEN", "")
        mSecurityPrefences.storeString("MOBILE", "")
        Login(null, null)
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}