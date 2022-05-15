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

    fun getTaks(token: String, idBoard: Int): LiveData<List<Task>>? {
        servicesLiveData = TaskRepository.getTasks(token, idBoard)
        return servicesLiveData
    }

    fun postTask(token: String, idBoard: Int, taskPost: TaskPost): LiveData<Task>? {
        serviceTaskLiveData = TaskRepository.postTask(token, idBoard, taskPost)
        return serviceTaskLiveData
    }

    fun putTask(token: String, idBoard: Int, idTask: Int,taskPost: TaskPost): LiveData<Task>? {
        serviceTaskLiveData = TaskRepository.putTask(token, idBoard, idTask, taskPost)
        return serviceTaskLiveData
    }

    fun deleteTask(token: String, idBoard: Int, idTask: Int): LiveData<Task>? {
        serviceTaskLiveData = TaskRepository.deleteTask(token, idBoard, idTask)
        return serviceTaskLiveData
    }
}