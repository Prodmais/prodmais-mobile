package com.jamessaboia.prodmaisapp.Interface

import com.jamessaboia.prodmaisapp.Model.Task
import com.jamessaboia.prodmaisapp.Model.TaskPost
import com.jamessaboia.prodmaisapp.Model.User
import com.jamessaboia.prodmaisapp.Model.UserPost
import retrofit2.Call
import retrofit2.http.*

interface TaskInterfaceApi {

    @GET("board/{idBoard}/task")
    fun getTasks(@Header("Authorization") authorization: String,  @Path("idBoard") idBoard: Int) : Call<List<Task>>

    @POST("board/{idBoard}/task")
    fun postTask(@Header("Authorization") authorization: String, @Path("idBoard") idBoard: Int, @Body taskPost: TaskPost) : Call<Task>

    @PUT("board/{idBoard}/task/{idTask}")
    fun putTask(@Header("Authorization") authorization: String, @Path("idBoard") idBoard: Int, @Path("idTask") idTask: Int, @Body taskPost: TaskPost) : Call<Task>

    @DELETE("board/{idBoard}/task/{idTask}")
    fun deleteTask(@Header("Authorization") authorization: String, @Path("idBoard") idBoard: Int, @Path("idTask") idTask: Int) : Call<Task>
}