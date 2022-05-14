package com.jamessaboia.prodmaisapp.Model

class Login(){

    companion object login {
        var token: String? = null
    }

    constructor(token: String) : this(){
        login.token = token
    }

}
