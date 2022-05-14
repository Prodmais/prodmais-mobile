package com.jamessaboia.prodmaisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.jamessaboia.prodmaisapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btLogin.setOnClickListener {
            validate()
        }

        binding.tvTelaCadastro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            this.startActivity(intent)
        }

    }

    private fun validate() {
        val result = arrayOf(validateEmail(), validatePassword())

        if (false in result) {
            return
        }
        Toast.makeText(this, "Bem-vindo!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
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