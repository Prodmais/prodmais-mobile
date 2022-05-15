package com.jamessaboia.prodmaisapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jamessaboia.prodmaisapp.Model.Session
import com.jamessaboia.prodmaisapp.Model.SessionPost
import com.jamessaboia.prodmaisapp.Model.User
import com.jamessaboia.prodmaisapp.Model.UserPost
import com.jamessaboia.prodmaisapp.Repository.SessionRepository
import com.jamessaboia.prodmaisapp.Repository.UserRepository

class SessionViewModel  : ViewModel() {

    var servicesLiveData: MutableLiveData<Session>? = null

    var servicesLiveDataBoard: MutableLiveData<Int>? = null

    fun login(sessionPost: SessionPost): LiveData<Session>? {
        servicesLiveData = SessionRepository.login(sessionPost)
        return servicesLiveData
    }

    fun getMobile(token: String): LiveData<Int>? {
        servicesLiveDataBoard = SessionRepository.getBoardMobile(token)
        return servicesLiveDataBoard
    }
}