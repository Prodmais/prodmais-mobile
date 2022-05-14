package com.jamessaboia.prodmaisapp.Interface

import com.jamessaboia.prodmaisapp.Model.Session
import com.jamessaboia.prodmaisapp.Model.SessionPost
import com.jamessaboia.prodmaisapp.Model.User
import com.jamessaboia.prodmaisapp.Model.UserPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SessionInterfaceApi {

    @POST("login")
    fun login(@Body sessionPost: SessionPost) : Call<Session>

}