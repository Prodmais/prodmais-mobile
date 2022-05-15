package com.jamessaboia.prodmaisapp.Model

class Login(){

    companion object login {
        var token: String? = null
        var idBoard: Int? = null
    }

    constructor(token: String, idBoard: Int) : this(){
        login.token = token
        login.idBoard = idBoard
    }

}
