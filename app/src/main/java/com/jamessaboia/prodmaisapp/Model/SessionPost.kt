package com.jamessaboia.prodmaisapp.Model

import com.google.gson.annotations.SerializedName

data class SessionPost  (
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
)