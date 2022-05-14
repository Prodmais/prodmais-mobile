package com.jamessaboia.prodmaisapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jamessaboia.prodmaisapp.Model.Task
import com.jamessaboia.prodmaisapp.Model.TaskPost
import com.jamessaboia.prodmaisapp.Model.User
import com.jamessaboia.prodmaisapp.Model.UserPost
import com.jamessaboia.prodmaisapp.Repository.TaskRepository
import com.jamessaboia.prodmaisapp.Repository.UserRepository

class TaskViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<List<Task>>? = null
    var serviceTaskLiveData: MutableLiveData<Task>? = null

    fun getTaks(token: String): LiveData<List<Task>>? {
        Log.v("TOKEN : ", token)
        servicesLiveData = TaskRepository.getTasks(token)
        return servicesLiveData
    }

    fun postTask(token: String, taskPost: TaskPost): LiveData<Task>? {
        Log.v("TOKEN : ", token)
        serviceTaskLiveData = TaskRepository.postTask(token, taskPost)
        return serviceTaskLiveData
    }

    fun putTask(token: String, idTask: Int,taskPost: TaskPost): LiveData<Task>? {
        Log.v("TOKEN : ", token)
        serviceTaskLiveData = TaskRepository.putTask(token, idTask, taskPost)
        return serviceTaskLiveData
    }

    fun deleteTask(token: String, idTask: Int): LiveData<Task>? {
        Log.v("TOKEN : ", token)
        serviceTaskLiveData = TaskRepository.deleteTask(token, idTask)
        return serviceTaskLiveData
    }
}