package com.jamessaboia.prodmaisapp.Model

import com.google.gson.annotations.SerializedName

data class Session  (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("token")
    var token: String,
)