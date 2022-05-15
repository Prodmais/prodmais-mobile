package com.jamessaboia.prodmaisapp.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jamessaboia.prodmaisapp.Database.RetrofitClient
import com.jamessaboia.prodmaisapp.Interface.SessionInterfaceApi
import com.jamessaboia.prodmaisapp.Interface.UserInterfaceApi
import com.jamessaboia.prodmaisapp.Model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SessionRepository {

    val remote = RetrofitClient.createService(SessionInterfaceApi::class.java)

    val serviceSession = MutableLiveData<Session>()
    val serviceSessionBoard = MutableLiveData<Int>()

    fun login(sessionPost: SessionPost): MutableLiveData<Session> {

        val call: Call<Session> = remote.login(sessionPost);

        call.enqueue(object: Callback<Session> {
            override fun onFailure(call: Call<Session>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
                serviceSession.value = null
            }

            override fun onResponse(
                call: Call<Session>,
                response: Response<Session>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                if(data != null) {
                    val id = data!!.id
                    val name = data!!.name
                    val email = data!!.email
                    val token = data!!.token

                    serviceSession.value = Session(id, name, email, token)
                } else{
                    serviceSession.value = null
                }
            }
        })

        return serviceSession
    }

    fun getBoardMobile(token: String): MutableLiveData<Int> {

        val call: Call<Board> = remote.getBoardMobile("Bearer $token")

        call.enqueue(object: Callback<Board> {
            override fun onFailure(call: Call<Board>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
                serviceSessionBoard.value = null
            }

            override fun onResponse(
                call: Call<Board>,
                response: Response<Board>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                if(data != null) {
                    val id = data!!.id

                    serviceSessionBoard.value = id
                } else{
                    serviceSessionBoard.value = null
                }
            }
        })

        return serviceSessionBoard
    }
}