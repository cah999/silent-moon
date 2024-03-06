package com.example.silentmoon.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ActivitySignUpBinding
import com.example.silentmoon.ui.welcome.WelcomeActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val name = binding.nameText.text.toString()
            val sharedPreferences = getSharedPreferences(getString(R.string.mysharedpref), MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val putString = editor.putString(getString(R.string.name), name)
            editor.apply()

            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
        binding.loginWithGoogleButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.btn_click_fail_1), Toast.LENGTH_SHORT).show()
        }

        binding.loginWithFacebookButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.button_click_fail_2), Toast.LENGTH_SHORT).show()
        }

        binding.privacyText2.setOnClickListener {
            Toast.makeText(this, getString(R.string.policy_toast), Toast.LENGTH_SHORT).show()
        }
    }
}