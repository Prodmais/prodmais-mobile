package com.jamessaboia.prodmaisapp.Database

import android.content.Context
import android.content.SharedPreferences

class SecurityPrefences(context: Context) {

    private val mSharedPreferences: SharedPreferences = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        this.mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getStoredString(key: String): String? {
        return this.mSharedPreferences.getString(key, "") ?: null
    }

}