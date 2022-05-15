package com.jamessaboia.prodmaisapp.Interface

import com.jamessaboia.prodmaisapp.Model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SessionInterfaceApi {

    @POST("login")
    fun login(@Body sessionPost: SessionPost) : Call<Session>

    @GET("board/mobile")
    fun getBoardMobile(@Header("Authorization") authorization: String) : Call<Board>
}