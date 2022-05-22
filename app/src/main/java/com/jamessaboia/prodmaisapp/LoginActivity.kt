package com.jamessaboia.prodmaisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jamessaboia.prodmaisapp.Database.SecurityPrefences
import com.jamessaboia.prodmaisapp.Model.Login
import com.jamessaboia.prodmaisapp.Model.SessionPost
import com.jamessaboia.prodmaisapp.ViewModel.SessionViewModel
import com.jamessaboia.prodmaisapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    lateinit var sessionViewModel: SessionViewModel

    private lateinit var mSecurityPrefences: SecurityPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        mSecurityPrefences = SecurityPrefences(this)
        sessionViewModel = ViewModelProvider(this).get(SessionViewModel::class.java)

        binding.btLogin.setOnClickListener {
            validate()
        }

        binding.tvTelaCadastro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            this.startActivity(intent)
            finish()
        }

    }

    private fun validate() {
        val result = arrayOf(validateEmail(), validatePassword())

        if (false in result) {
            return
        }

        val email = binding.email.editText?.text.toString()
        val senha = binding.senha.editText?.text.toString()

        val session = SessionPost(email, senha)

        sessionViewModel.login(session)!!.observe(this, Observer { session ->
            if(session != null){
                sessionViewModel.getMobile(session.token)!!.observe(this, Observer { mobile ->
                    Login(session.token, mobile)
                    mSecurityPrefences.storeString("TOKEN", session.token)
                    mSecurityPrefences.storeString("MOBILE", mobile.toString())
                    Toast.makeText(this, "Bem-vindo!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                })
            } else {
                Toast.makeText(this, "Senha/Email inválidos!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun validateEmail(): Boolean {
        val email = binding.email.editText?.text.toString()
        return if (email.isEmpty()) {
            binding.email.error = "Campo obrigatório"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = "Email inválido"
            false
        } else {
            binding.email.error = null
            true
        }
    }


    private fun validatePassword(): Boolean {
        val senha = binding.senha.editText?.text.toString()

        return if (senha.isEmpty()) {
            binding.senha.error = "Campo obrigatório"
            false
        } else {
            binding.senha.error = null
            true
        }
    }
}