package com.jamessaboia.prodmaisapp.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jamessaboia.prodmaisapp.Database.RetrofitClient
import com.jamessaboia.prodmaisapp.Interface.SessionInterfaceApi
import com.jamessaboia.prodmaisapp.Interface.UserInterfaceApi
import com.jamessaboia.prodmaisapp.Model.Session
import com.jamessaboia.prodmaisapp.Model.SessionPost
import com.jamessaboia.prodmaisapp.Model.User
import com.jamessaboia.prodmaisapp.Model.UserPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SessionRepository {

    val remote = RetrofitClient.createService(SessionInterfaceApi::class.java)

    val serviceSession = MutableLiveData<Session>()

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
}