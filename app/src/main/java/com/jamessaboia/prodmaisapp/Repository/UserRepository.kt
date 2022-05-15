package com.jamessaboia.prodmaisapp.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jamessaboia.prodmaisapp.Database.RetrofitClient
import com.jamessaboia.prodmaisapp.Interface.UserInterfaceApi
import com.jamessaboia.prodmaisapp.Model.User
import com.jamessaboia.prodmaisapp.Model.UserPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepository {

    val remote = RetrofitClient.createService(UserInterfaceApi::class.java)

    val serviceUser = MutableLiveData<User>()

    fun getUser(token: String): MutableLiveData<User> {

        val call: Call<User> = remote.getUser("Bearer $token");

        call.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                val id = data!!.id
                val name = data!!.name
                val email = data!!.email
                val password = data!!.password
                val updatedAt = data!!.updatedAt
                val createdAt = data!!.createdAt
                val deletedAt = data!!.deletedAt

                serviceUser.value = User(id, name, email, password, updatedAt, createdAt, deletedAt)
            }
        })

        return serviceUser
    }

    fun postUser(userPost: UserPost): MutableLiveData<User> {

        val call: Call<User> = remote.postUser(userPost);

        call.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
                serviceUser.value = null
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                if(data != null) {
                    val id = data!!.id
                    val name = data!!.name
                    val email = data!!.email
                    val password = data!!.password
                    val updatedAt = data!!.updatedAt
                    val createdAt = data!!.createdAt
                    val deletedAt = data!!.deletedAt

                    serviceUser.value = User(id, name, email, password, updatedAt, createdAt, deletedAt)
                } else {
                    serviceUser.value = null
                }
            }
        })

        return serviceUser
    }
}