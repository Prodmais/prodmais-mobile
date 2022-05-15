package com.jamessaboia.prodmaisapp.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Task(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("boardId")
    var boardId: Int? = null,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("status")
    var status: String,
    @SerializedName("updatedAt")
    var updatedAt: Date,
    @SerializedName("createdAt")
    var createdAt: Date,
    @SerializedName("endDate")
    var endDate: Date? = null,
) : Serializable