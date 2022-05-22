package com.jamessaboia.prodmaisapp.Model

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserPost (
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
)