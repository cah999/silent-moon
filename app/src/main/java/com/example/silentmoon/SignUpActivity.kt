package com.example.silentmoon

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
        binding.loginWithGoogleButton.setOnClickListener {
            Toast.makeText(this, "А вот и нет", Toast.LENGTH_SHORT).show()
        }

        binding.loginWithFacebookButton.setOnClickListener {
            Toast.makeText(this, "Не хочу...", Toast.LENGTH_SHORT).show()
        }

        binding.privacyText2.setOnClickListener {
            Toast.makeText(this, "Тут будет политика конфиденциальности", Toast.LENGTH_SHORT).show()
        }
    }
}