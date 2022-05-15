package com.jamessaboia.prodmaisapp.Interface

import com.jamessaboia.prodmaisapp.Model.User
import com.jamessaboia.prodmaisapp.Model.UserPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserInterfaceApi {

    @GET("user")
    fun getUser(@Header("Authorization") authorization: String) : Call<User>

    @POST("user")
    fun postUser(@Body userPost: UserPost) : Call<User>
}