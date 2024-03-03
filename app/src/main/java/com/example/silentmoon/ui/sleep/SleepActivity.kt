package com.example.silentmoon.ui.sleep

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.databinding.ActivitySleepBinding

class SleepActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySleepBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySleepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sleepGetStartedBtn.setOnClickListener {
            finish()
        }
    }
}