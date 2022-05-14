package com.jamessaboia.prodmaisapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jamessaboia.prodmaisapp.Model.User
import com.jamessaboia.prodmaisapp.Model.UserPost
import com.jamessaboia.prodmaisapp.Repository.UserRepository

class UserViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<User>? = null

    fun getUser(token: String): LiveData<User>? {
        servicesLiveData = UserRepository.getUser(token)
        return servicesLiveData
    }

    fun postUser(userPost: UserPost): LiveData<User>? {
        servicesLiveData = UserRepository.postUser(userPost)
        return servicesLiveData
    }
}