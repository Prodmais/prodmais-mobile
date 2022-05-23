package com.jamessaboia.prodmaisapp.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jamessaboia.prodmaisapp.Database.RetrofitClient
import com.jamessaboia.prodmaisapp.Interface.TaskInterfaceApi
import com.jamessaboia.prodmaisapp.Interface.UserInterfaceApi
import com.jamessaboia.prodmaisapp.Model.Task
import com.jamessaboia.prodmaisapp.Model.TaskPost
import com.jamessaboia.prodmaisapp.Model.User
import com.jamessaboia.prodmaisapp.Model.UserPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TaskRepository {

    val remote = RetrofitClient.createService(TaskInterfaceApi::class.java)

    val serviceTask = MutableLiveData<Task>()
    val serviceListTask = MutableLiveData<List<Task>>()

    fun getTasks(token: String, idBoard: Int): MutableLiveData<List<Task>> {

        val call: Call<List<Task>> = remote.getTasks("Bearer $token", idBoard);

        call.enqueue(object: Callback<List<Task>> {
            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<Task>>,
                response: Response<List<Task>>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val datas = response.body()

                if(datas != null) {
                    var list: MutableList<Task> = mutableListOf()
                    for (data in datas!!) {
                        val id = data!!.id
                        val boardId = data!!.boardId
                        val name = data!!.name
                        val email = data!!.description
                        val status = data!!.status
                        val updatedAt = data!!.updatedAt
                        val createdAt = data!!.createdAt
                        val deletedAt = data!!.endDate

                        val task =
                            Task(id, boardId, name, email, status, updatedAt, createdAt, deletedAt)

                        list.add(task)
                    }
                    serviceListTask.value = list
                }
            }
        })

        return serviceListTask
    }

    fun postTask(token: String, idBoard: Int, taskPost: TaskPost): MutableLiveData<Task> {

        val call: Call<Task> = remote.postTask("Bearer $token", idBoard, taskPost);

        call.enqueue(object: Callback<Task> {
            override fun onFailure(call: Call<Task>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<Task>,
                response: Response<Task>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()
                val id = data?.id
                val boardId = data!!.boardId
                val name = data!!.name
                val email = data?.description
                val status = data!!.status
                val updatedAt = data!!.updatedAt
                val createdAt = data!!.createdAt
                val deletedAt = data?.endDate

                val task = Task(id, boardId, name, email, status, updatedAt, createdAt, deletedAt)

                serviceTask.value = task
            }
        })

        return serviceTask
    }

    fun putTask(token: String, idBoard: Int, idTask: Int, taskPost: TaskPost): MutableLiveData<Task> {

        val call: Call<Task> = remote.putTask("Bearer $token", idBoard, idTask, taskPost);

        call.enqueue(object: Callback<Task> {
            override fun onFailure(call: Call<Task>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<Task>,
                response: Response<Task>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()
                val id = data!!.id
                val boardId = data!!.boardId
                val name = data!!.name
                val email = data!!.description
                val status = data!!.status
                val updatedAt = data!!.updatedAt
                val createdAt = data!!.createdAt
                val deletedAt = data!!.endDate

                val task = Task(id, boardId, name, email, status, updatedAt, createdAt, deletedAt)

                serviceTask.value = task
            }
        })

        return serviceTask
    }

    fun deleteTask(token: String, idBoard: Int, idTask: Int): MutableLiveData<Task> {

        val call: Call<Task> = remote.deleteTask("Bearer $token", idBoard, idTask);

        call.enqueue(object: Callback<Task> {
            override fun onFailure(call: Call<Task>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<Task>,
                response: Response<Task>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()
                val id = data!!.id
                val boardId = data!!.boardId
                val name = data!!.name
                val email = data!!.description
                val status = data!!.status
                val updatedAt = data!!.updatedAt
                val createdAt = data!!.createdAt
                val deletedAt = data!!.endDate

                val task = Task(id, boardId, name, email, status, updatedAt, createdAt, deletedAt)

                serviceTask.value = task
            }
        })

        return serviceTask
    }
}