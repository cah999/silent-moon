package com.example.silentmoon

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }

        binding.haveAccount2.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginWithGoogleButton.setOnClickListener {
            Toast.makeText(this, "Ну уж нет", Toast.LENGTH_SHORT).show()
        }

        binding.loginWithFacebookButton.setOnClickListener {
            Toast.makeText(this, "Мета запрещена в РФ", Toast.LENGTH_SHORT).show()
        }

        binding.forgotPasswordText.setOnClickListener {
            Toast.makeText(this, "Увы", Toast.LENGTH_SHORT).show()
        }
    }
}