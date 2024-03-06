package com.example.silentmoon.ui.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ActivitySignInBinding
import com.example.silentmoon.ui.signup.SignUpActivity
import com.example.silentmoon.ui.welcome.WelcomeActivity

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
            Toast.makeText(this, getString(R.string.button_click_failed_3), Toast.LENGTH_SHORT)
                .show()
        }

        binding.loginWithFacebookButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.button_click_failed_4), Toast.LENGTH_SHORT)
                .show()
        }

        binding.forgotPasswordText.setOnClickListener {
            Toast.makeText(this, getString(R.string.unlucky), Toast.LENGTH_SHORT).show()
        }
    }
}