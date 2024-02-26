package com.example.silentmoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.silentmoon.databinding.ActivityMainBinding
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
            Toast.makeText(this, "Нет, ты не сделаешь этого", Toast.LENGTH_SHORT).show()
        }

        binding.loginWithFacebookButton.setOnClickListener {
            Toast.makeText(this, "Нет, ты не сделаешь этого", Toast.LENGTH_SHORT).show()
        }

        binding.forgotPasswordText.setOnClickListener {
            Toast.makeText(this, "Увы", Toast.LENGTH_SHORT).show()
        }
    }
}