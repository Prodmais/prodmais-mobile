package com.jamessaboia.prodmaisapp.Model

import com.google.gson.annotations.SerializedName
import java.util.*

data class User (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("updatedAt")
    var updatedAt: Date,
    @SerializedName("createdAt")
    var createdAt: Date,
    @SerializedName("deletedAt")
    var deletedAt: Date? = null,
)