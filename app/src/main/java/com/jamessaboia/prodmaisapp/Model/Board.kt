package com.jamessaboia.prodmaisapp.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Board(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("userId")
    var userId: Int? = null,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("isMobile")
    var isMobile: Boolean,
    @SerializedName("updatedAt")
    var updatedAt: Date,
    @SerializedName("createdAt")
    var createdAt: Date,
)