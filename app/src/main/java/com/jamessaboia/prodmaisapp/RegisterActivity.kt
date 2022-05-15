package com.jamessaboia.prodmaisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jamessaboia.prodmaisapp.Model.Login
import com.jamessaboia.prodmaisapp.Model.SessionPost
import com.jamessaboia.prodmaisapp.Model.UserPost
import com.jamessaboia.prodmaisapp.ViewModel.SessionViewModel
import com.jamessaboia.prodmaisapp.ViewModel.UserViewModel
import com.jamessaboia.prodmaisapp.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    lateinit var userViewModel: UserViewModel
    lateinit var sessionViewModel: SessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sessionViewModel = ViewModelProvider(this).get(SessionViewModel::class.java)

        binding.btRegister.setOnClickListener {
            validate()
        }


    }

    private fun validate() {
        val result = arrayOf(validateName(), validateEmail(), validatePassword())

        if (false in result) {
            return
        }

        val nome = binding.name.editText?.text.toString()
        val email = binding.email.editText?.text.toString()
        val senha = binding.senha.editText?.text.toString()

        val user = UserPost(nome, email, senha)

        //var sessionUser: SessionPost? = null

        userViewModel.postUser(user)!!.observe(this, Observer { user ->
            if(user != null) {
                val intent = Intent(applicationContext, Login::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Falha no Cadastro!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun validateName(): Boolean {
        val nome = binding.name.editText?.text.toString()
        return if (nome.isEmpty()) {
            binding.name.error = "Campo obrigatório"
            false
        } else {
            binding.name.error = null
            true
        }
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
//    private fun validateTerms(): Boolean {
//        val terms = binding.cbTerms
//
//    }


    }