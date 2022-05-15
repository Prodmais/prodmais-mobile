package com.jamessaboia.prodmaisapp.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class TaskPost(
    var name: String,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("status")
    var status: Int,
)