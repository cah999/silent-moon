package com.example.silentmoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.silentmoon.databinding.ActivityMainBinding
import com.example.silentmoon.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}