package com.jamessaboia.prodmaisapp.Interface

import com.jamessaboia.prodmaisapp.Model.Task
import com.jamessaboia.prodmaisapp.Model.TaskPost
import com.jamessaboia.prodmaisapp.Model.User
import com.jamessaboia.prodmaisapp.Model.UserPost
import retrofit2.Call
import retrofit2.http.*

interface TaskInterfaceApi {

    @GET("board/21/task")
    fun getTasks(@Header("Authorization") authorization: String) : Call<List<Task>>

    @POST("board/21/task")
    fun postTask(@Header("Authorization") authorization: String, @Body taskPost: TaskPost) : Call<Task>

    @PUT("board/21/task/{idTask}")
    fun putTask(@Header("Authorization") authorization: String, @Path("idTask") idTask: Int, @Body taskPost: TaskPost) : Call<Task>

    @DELETE("board/21/task/{idTask}")
    fun deleteTask(@Header("Authorization") authorization: String, @Path("idTask") idTask: Int) : Call<Task>
}